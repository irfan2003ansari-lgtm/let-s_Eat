package com.jsp.lets_eat.CartModule.Dto;

import com.jsp.lets_eat.CartModule.Model.Cart;
import com.jsp.lets_eat.CartModule.Model.CartItem;
import com.jsp.lets_eat.ResturantModule.Model.FoodCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartResponse {
    private Double totalPrice;
    private List<CartItem> cartItemList;

    public CartResponse(Cart cart) {
        this.totalPrice = cart.getTotalPrice();
        this.cartItemList = cart.getCartItemList();
    }
}
