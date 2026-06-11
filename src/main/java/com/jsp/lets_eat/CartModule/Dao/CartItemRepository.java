package com.jsp.lets_eat.CartModule.Dao;

import com.jsp.lets_eat.CartModule.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
