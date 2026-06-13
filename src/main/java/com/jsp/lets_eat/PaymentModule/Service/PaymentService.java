package com.jsp.lets_eat.PaymentModule.Service;

import com.jsp.lets_eat.PaymentModule.Dto.PaymentRequest;
import com.jsp.lets_eat.PaymentModule.Dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse getPaymentById(Long paymentId);
    PaymentResponse getPaymentByOrderId(Long orderId);
}
