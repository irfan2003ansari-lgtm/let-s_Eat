package com.jsp.lets_eat.PaymentModule.Dao;

import com.jsp.lets_eat.PaymentModule.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrder_OrderId(Long orderId);
}
