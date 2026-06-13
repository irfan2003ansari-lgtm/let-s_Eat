package com.jsp.lets_eat.OrderModule.Service;

import com.jsp.lets_eat.OrderModule.Dto.OrderRequest;
import com.jsp.lets_eat.OrderModule.Dto.OrderResponse;
import com.jsp.lets_eat.OrderModule.Model.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    OrderResponse createOrder(OrderRequest request);
    OrderResponse getOrderById(Long orderId);
    List<OrderResponse> findOrdersByUserId(Integer userId);
    OrderResponse cancelOrder(Long orderId);
    List<OrderResponse> findAllOrders();
    public OrderResponse updateStatus(Long orderId, Status status);

}
