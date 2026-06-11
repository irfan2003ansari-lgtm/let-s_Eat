package com.jsp.lets_eat.CartModule.Dao;

import com.jsp.lets_eat.CartModule.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository <Cart,Long>{

    Optional<Cart> findByUser_Id(Integer userId);
}
