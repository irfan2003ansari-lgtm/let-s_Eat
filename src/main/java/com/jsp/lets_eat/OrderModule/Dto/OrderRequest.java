package com.jsp.lets_eat.OrderModule.Dto;

import com.jsp.lets_eat.PaymentModule.Model.PaymentMethod;
import lombok.Data;

@Data
public class OrderRequest {

    private Integer userId;
    private PaymentMethod paymentMethod;

}
