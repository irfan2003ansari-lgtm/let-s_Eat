package com.jsp.lets_eat.OrderModule.Dao;

import com.jsp.lets_eat.OrderModule.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
