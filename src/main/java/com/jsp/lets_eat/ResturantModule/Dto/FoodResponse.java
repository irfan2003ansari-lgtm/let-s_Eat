package com.jsp.lets_eat.ResturantModule.Dto;

import com.jsp.lets_eat.ResturantModule.Model.FoodCategory;
import com.jsp.lets_eat.ResturantModule.Model.FoodItem;
import lombok.Data;

@Data
public class FoodResponse {
    private String foodName;
    private String description;
    private Double price;
    private FoodCategory catogry;
    private String imageUrl;
    private Integer stock;
    private Boolean isAvailable;
    private String resturantName;

    public FoodResponse(FoodItem foodItem){
        this.foodName=foodItem.getFoodName();
        this.description=foodItem.getDescription();
        this.price=foodItem.getPrice();
        this.catogry=foodItem.getCatogry();
        this.imageUrl=foodItem.getImageUrl();
        this.stock=foodItem.getStock();
        this.isAvailable=foodItem.getIsAvailable();
        this.resturantName=foodItem.getResturant().getRessturantName();
    }
}
