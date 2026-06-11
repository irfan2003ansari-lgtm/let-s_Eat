package com.jsp.lets_eat.CartModule.Service;

import com.jsp.lets_eat.CartModule.Dto.CartItemRequest;
import com.jsp.lets_eat.CartModule.Dto.CartResponse;

public interface CartService {

    CartResponse createCart(CartItemRequest cartItemRequest);
    CartResponse findByUserId(Integer userId);
    CartResponse increaseQuantity(Long foodItemId,Integer userId);
    CartResponse decreaseQuantity(Long foodItemId,Integer userId);

}
