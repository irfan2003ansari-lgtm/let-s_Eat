package com.jsp.lets_eat.OrderModule.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.lets_eat.CartModule.Model.CartItem;
import com.jsp.lets_eat.ResturantModule.Model.FoodCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private Long foodItemId;
    private Integer quantity;
    private Double price;
    private String foodName;
    private String description;
    @Enumerated(EnumType.STRING)
    private FoodCategory catogry;
    private String imageUrl;
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;


    public OrderItem(CartItem cartItem){
        this.foodItemId = cartItem.getFoodItemId();
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getPrice();
        this.foodName = cartItem.getFoodName();
        this.description = cartItem.getDescription();
        this.catogry = cartItem.getCatogry();
        this.imageUrl = cartItem.getImageUrl();
        this.totalPrice = cartItem.getTotalPrice();
    }
}
