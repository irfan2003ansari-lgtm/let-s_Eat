package com.jsp.lets_eat.PaymentModule.Service;

import com.jsp.lets_eat.CommonModule.Exception.UserException;
import com.jsp.lets_eat.OrderModule.Dao.OrderRepository;
import com.jsp.lets_eat.OrderModule.Model.Order;
import com.jsp.lets_eat.PaymentModule.Dao.PaymentRepository;
import com.jsp.lets_eat.PaymentModule.Dto.PaymentRequest;
import com.jsp.lets_eat.PaymentModule.Dto.PaymentResponse;
import com.jsp.lets_eat.PaymentModule.Model.Payment;
import com.jsp.lets_eat.PaymentModule.Model.PaymentMethod;
import com.jsp.lets_eat.PaymentModule.Model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;



    @Override
    public PaymentResponse getPaymentById(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new UserException("Payment not found with id "
                                + paymentId));

        return new PaymentResponse(payment);
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {

        Payment payment = paymentRepository.findByOrder_OrderId(orderId)
                .orElseThrow(() ->
                        new UserException(
                                "Payment not found for order id "
                                        + orderId));

        return new PaymentResponse(payment);
    }
}
