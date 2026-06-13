package com.jsp.lets_eat.PaymentModule.Dto;

import com.jsp.lets_eat.PaymentModule.Model.PaymentMethod;
import lombok.Data;

@Data
public class PaymentRequest {

    private Long orderId;
    private PaymentMethod paymentMethod;
}