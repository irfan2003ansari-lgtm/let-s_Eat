package com.jsp.lets_eat.CartModule.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.lets_eat.CartModule.Dto.CartItemRequest;
import com.jsp.lets_eat.ResturantModule.Model.FoodCategory;
import com.jsp.lets_eat.ResturantModule.Model.FoodItem;
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
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
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
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    public CartItem(CartItemRequest cartItemRequest,FoodItem foodItem){
        this.foodItemId=cartItemRequest.getFoodItemId();
        this.price=foodItem.getPrice();
        this.quantity=1;
        this.foodName=foodItem.getFoodName();
        this.description=foodItem.getDescription();
        this.catogry=foodItem.getCatogry();
        this.imageUrl=foodItem.getImageUrl();
        this.totalPrice=foodItem.getPrice();
    }


}
