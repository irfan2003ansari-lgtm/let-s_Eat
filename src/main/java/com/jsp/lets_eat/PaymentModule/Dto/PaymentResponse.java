package com.jsp.lets_eat.PaymentModule.Dto;

import com.jsp.lets_eat.PaymentModule.Model.Payment;
import com.jsp.lets_eat.PaymentModule.Model.PaymentMethod;
import com.jsp.lets_eat.PaymentModule.Model.PaymentStatus;
import lombok.Data;

@Data
public class PaymentResponse {

    private Long paymentId;
    private Long orderId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    public PaymentResponse(Payment payment){
        this.paymentId = payment.getPaymentId();
        this.orderId = payment.getOrder().getOrderId();
        this.amount = payment.getAmount();
        this.paymentMethod = payment.getPaymentMethod();
        this.paymentStatus = payment.getPaymentStatus();
    }
}
