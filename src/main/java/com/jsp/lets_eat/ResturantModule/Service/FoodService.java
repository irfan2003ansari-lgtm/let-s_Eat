package com.jsp.lets_eat.ResturantModule.Service;

import com.jsp.lets_eat.ResturantModule.Dto.FoodRequest;
import com.jsp.lets_eat.ResturantModule.Dto.FoodResponse;
import com.jsp.lets_eat.ResturantModule.Dto.ResturantResponse;
import com.jsp.lets_eat.ResturantModule.Model.FoodItem;

import java.util.List;

public interface FoodService {
    FoodResponse createFoodItem(FoodRequest foodRequest);
    FoodResponse updateStock(Long foodId, Integer newStock);
    List<ResturantResponse> getResturantMenu(String foodname);

}
