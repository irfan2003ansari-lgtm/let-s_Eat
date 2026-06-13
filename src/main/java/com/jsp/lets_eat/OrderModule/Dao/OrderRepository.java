package com.jsp.lets_eat.OrderModule.Dao;

import com.jsp.lets_eat.OrderModule.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_Id(Integer userId);
}
