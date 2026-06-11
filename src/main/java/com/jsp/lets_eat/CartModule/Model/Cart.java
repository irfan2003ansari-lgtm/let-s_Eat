package com.jsp.lets_eat.CartModule.Model;

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
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_Id")
    private User user;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> cartItemList=new ArrayList<>();
    private Double totalPrice;


}
