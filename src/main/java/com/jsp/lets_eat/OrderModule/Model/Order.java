package com.jsp.lets_eat.OrderModule.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.lets_eat.PaymentModule.Model.Payment;
import com.jsp.lets_eat.UserModule.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long orderId;
        @ManyToOne
        @JoinColumn(name = "user_id")
        @JsonIgnore
        private User user;
        @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
        @JsonIgnore
        private List<OrderItem> orderItems=new ArrayList<>();
        private Double totalPrice;
        @Enumerated(EnumType.STRING)
        private Status status;

        @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
        @JsonIgnore
        private Payment payment;

}
