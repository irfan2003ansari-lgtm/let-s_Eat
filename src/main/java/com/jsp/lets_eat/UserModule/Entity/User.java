package com.jsp.lets_eat.UserModule.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.lets_eat.CartModule.Model.Cart;
import com.jsp.lets_eat.OrderModule.Model.Order;
import com.jsp.lets_eat.ResturantModule.Model.Resturant;
import com.jsp.lets_eat.UserModule.Dto.UserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Long phone;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean active;
    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private Resturant resturant;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> orders;


    public User(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.phone = userRequest.getPhone();
        this.email = userRequest.getEmail();

    }
}
