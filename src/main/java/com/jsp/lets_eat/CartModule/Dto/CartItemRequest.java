package com.jsp.lets_eat.CartModule.Dto;

import lombok.Data;

@Data
public class CartItemRequest {

    private Long foodItemId;
    private Integer userId;

}
