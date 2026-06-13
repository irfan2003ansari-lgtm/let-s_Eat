package com.jsp.lets_eat.OrderModule.Service;

import com.jsp.lets_eat.CartModule.Dao.CartRepository;
import com.jsp.lets_eat.CartModule.Model.Cart;
import com.jsp.lets_eat.CartModule.Model.CartItem;
import com.jsp.lets_eat.CommonModule.Exception.UserException;
import com.jsp.lets_eat.OrderModule.Dao.OrderRepository;
import com.jsp.lets_eat.OrderModule.Dto.OrderRequest;
import com.jsp.lets_eat.OrderModule.Dto.OrderResponse;
import com.jsp.lets_eat.OrderModule.Model.Order;
import com.jsp.lets_eat.OrderModule.Model.OrderItem;
import com.jsp.lets_eat.OrderModule.Model.Status;
import com.jsp.lets_eat.PaymentModule.Dao.PaymentRepository;
import com.jsp.lets_eat.PaymentModule.Model.Payment;
import com.jsp.lets_eat.PaymentModule.Model.PaymentMethod;
import com.jsp.lets_eat.PaymentModule.Model.PaymentStatus;
import com.jsp.lets_eat.ResturantModule.Dao.FoodRepository;
import com.jsp.lets_eat.ResturantModule.Model.FoodItem;
import com.jsp.lets_eat.UserModule.Entity.User;
import com.jsp.lets_eat.UserModule.dao.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserException("User not found with id " + request.getUserId()));

        Cart cart = cartRepository.findByUser_Id(request.getUserId())
                .orElseThrow(() ->
                        new UserException("Cart not found for user with id " + request.getUserId()));

        if (cart.getCartItemList() == null || cart.getCartItemList().isEmpty()) {
            throw new UserException(
                    "Cart is empty for user with id " + request.getUserId());
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(Status.PLACED);

        for (CartItem cartItem : cart.getCartItemList()) {

            FoodItem foodItem = foodRepository.findById(cartItem.getFoodItemId())
                    .orElseThrow(() ->
                            new UserException("Food Item not found with id "
                                    + cartItem.getFoodItemId()));

            // Stock Validation
            if (foodItem.getStock() < cartItem.getQuantity()) {
                throw new UserException(
                        foodItem.getFoodName() + " has only "
                                + foodItem.getStock() + " items left in stock");
            }

            // Reduce Stock
            foodItem.setStock(
                    foodItem.getStock() - cartItem.getQuantity());

            foodRepository.save(foodItem);

            // Create Order Item
            OrderItem orderItem = new OrderItem(cartItem);
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }

        Order savedOrder = orderRepository.save(order);

        // Create Payment
        Payment payment = new Payment();

        payment.setOrder(savedOrder);
        payment.setAmount(savedOrder.getTotalPrice());
        payment.setPaymentMethod(request.getPaymentMethod());

        if (request.getPaymentMethod() == PaymentMethod.CASH_ON_DELIVERY) {
            payment.setPaymentStatus(PaymentStatus.PENDING);
        } else {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
        }

        Payment savedPayment = paymentRepository.save(payment);

        // Link payment with order
        savedOrder.setPayment(savedPayment);


        // Clear Cart
        cart.getCartItemList().clear();
        cart.setTotalPrice(0.0);

        cartRepository.save(cart);

        return new OrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order=orderRepository.findById(orderId).orElseThrow(()-> new UserException("Order not found with id "+orderId));
        return new OrderResponse(order);
    }

    @Override
    public List<OrderResponse> findOrdersByUserId(Integer userId) {

        User user=userRepository.findById(userId).orElseThrow(()-> new UserException("User not found with id "+userId));
        List<Order> orders=orderRepository.findByUser_Id(userId);
          List<OrderResponse> orderResponses=new ArrayList<>();

          for (Order order:orders){
              orderResponses.add(new OrderResponse(order));
          }
          return orderResponses;
    }

    @Override
    @Transactional
    public OrderResponse cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new UserException("Order not found with id " + orderId));

        if (order.getStatus() == Status.CANCELLED) {
            throw new UserException(
                    "Order with id " + orderId + " is already cancelled");
        }

        if (order.getStatus() == Status.DELIVERED) {
            throw new UserException(
                    "Delivered order cannot be cancelled");
        }

        // Restore stock
        for (OrderItem orderItem : order.getOrderItems()) {

            FoodItem foodItem = foodRepository.findById(orderItem.getFoodItemId())
                    .orElseThrow(() ->
                            new UserException(
                                    "Food item not found with id "
                                            + orderItem.getFoodItemId()));

            foodItem.setStock(
                    foodItem.getStock() + orderItem.getQuantity());

            foodRepository.save(foodItem);
        }

        // Update Payment Status
        Payment payment = order.getPayment();

        if (payment != null) {

            if (payment.getPaymentStatus() == PaymentStatus.SUCCESS) {

                // Later you can add refund logic here
                payment.setPaymentStatus(PaymentStatus.REFUNDED);

            } else if (payment.getPaymentStatus() == PaymentStatus.PENDING) {

                payment.setPaymentStatus(PaymentStatus.CANCELLED);
            }

            paymentRepository.save(payment);
        }

        order.setStatus(Status.CANCELLED);

        Order cancelledOrder = orderRepository.save(order);

        return new OrderResponse(cancelledOrder);
    }

    @Override
    public List<OrderResponse> findAllOrders() {

        List<Order> orders=orderRepository.findAll();
        List<OrderResponse> orderResponses=new ArrayList<>();

        for (Order order:orders){
            orderResponses.add(new OrderResponse(order));
        }
        return orderResponses;
    }

    @Override
    public OrderResponse updateStatus(Long orderId, Status status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new UserException("Order not found with id " + orderId));

        if (order.getStatus() == Status.CANCELLED) {
            throw new UserException(
                    "Cancelled order cannot be updated");
        }

        if (order.getStatus() == Status.DELIVERED) {
            throw new UserException(
                    "Delivered order cannot be updated");
        }

        order.setStatus(status);

        Payment payment = order.getPayment();

        if (payment != null) {

            // COD payment becomes successful when delivered
            if (status == Status.DELIVERED
                    && payment.getPaymentMethod() == PaymentMethod.CASH_ON_DELIVERY) {

                payment.setPaymentStatus(PaymentStatus.SUCCESS);
            }

            paymentRepository.save(payment);
        }

        Order updatedOrder = orderRepository.save(order);

        return new OrderResponse(updatedOrder);
    }

}