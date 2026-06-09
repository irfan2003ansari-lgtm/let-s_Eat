package com.jsp.lets_eat.ResturantModule.Model;

import com.jsp.lets_eat.ResturantModule.Dto.FoodRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "food_seq", sequenceName = "food_seq", allocationSize = 1 ,initialValue = 1000)
    private Long id;
    private String foodName;
    private String description;
    private Double price;
    @Enumerated(EnumType.STRING)
    private FoodCategory catogry;
    private String imageUrl;
    private Integer stock;
    private Boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "resturant_id")
    private Resturant resturant;

    public FoodItem(FoodRequest foodRequest) {
        this.foodName=foodRequest.getFoodName();
        this.description=foodRequest.getDescription();
        this.price=foodRequest.getPrice();
        this.catogry=foodRequest.getCatogry();
        this.imageUrl=foodRequest.getImageUrl();
        this.stock=foodRequest.getStock();

    }

}
