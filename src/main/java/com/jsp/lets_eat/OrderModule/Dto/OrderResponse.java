package com.jsp.lets_eat.OrderModule.Dto;

import com.jsp.lets_eat.OrderModule.Model.Order;
import com.jsp.lets_eat.OrderModule.Model.OrderItem;
import com.jsp.lets_eat.OrderModule.Model.Status;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {

    private Long orderId;
    private Integer userId;
    private Double totalPrice;
    private Status status;
    private List<OrderItem> orderItems;

    public OrderResponse(Order order) {

        this.orderId = order.getOrderId();
        this.userId = order.getUser().getId();
        this.totalPrice = order.getTotalPrice();
        this.status = order.getStatus();
        this.orderItems = order.getOrderItems();
    }
}