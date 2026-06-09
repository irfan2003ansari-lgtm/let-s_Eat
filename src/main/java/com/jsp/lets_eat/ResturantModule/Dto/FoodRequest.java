package com.jsp.lets_eat.ResturantModule.Dto;

import com.jsp.lets_eat.ResturantModule.Model.FoodCategory;
import lombok.Data;

@Data
public class FoodRequest {
    private String foodName;
    private String description;
    private Double price;
    private FoodCategory catogry;
    // use enum class for catogory
    private String imageUrl;
    private Integer stock;
    private Long resturant_id;
}
