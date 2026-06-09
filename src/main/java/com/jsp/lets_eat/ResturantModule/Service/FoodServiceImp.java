package com.jsp.lets_eat.ResturantModule.Service;

import com.jsp.lets_eat.CommonModule.Exception.FoodException;
import com.jsp.lets_eat.CommonModule.Exception.ResturantException;
import com.jsp.lets_eat.ResturantModule.Dao.FoodRepository;
import com.jsp.lets_eat.ResturantModule.Dao.ResturantRepository;
import com.jsp.lets_eat.ResturantModule.Dto.FoodRequest;
import com.jsp.lets_eat.ResturantModule.Dto.FoodResponse;
import com.jsp.lets_eat.ResturantModule.Dto.ResturantResponse;
import com.jsp.lets_eat.ResturantModule.Model.FoodItem;
import com.jsp.lets_eat.ResturantModule.Model.Resturant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImp implements FoodService {
    private final FoodRepository foodRepository;
    private final ResturantRepository resturantRepository;

    @Override
    public FoodResponse createFoodItem(FoodRequest foodRequest) {
        FoodItem food=new FoodItem(foodRequest);
        food.setIsAvailable(true);
        Long id=foodRequest.getResturant_id();
        Resturant resturant=resturantRepository.findById(id).orElseThrow(() -> new ResturantException("Resturant not found with id: " + id));
       if(resturant.getActive()){
           food.setResturant(resturant);
          List<FoodItem> foodItems=resturant.getFood();
          foodItems.add(food);
          resturant.setFood(foodItems);
       }else {
           throw new ResturantException("Resturant with id: " + id + " is inactive");
       }
       resturantRepository.save(resturant);

        return new FoodResponse(food);


    }

    @Override
    public FoodResponse updateStock(Long foodId, Integer newStock) {
        FoodItem food=foodRepository.findById(foodId).orElseThrow(() -> new FoodException("Food item not found with id: " + foodId));
        food.setStock(food.getStock()+newStock);
        if (food.getStock() > 0) {
            food.setIsAvailable(true);
        } else {
            food.setIsAvailable(false);
        }
        FoodItem updatedFood=foodRepository.save(food);
        FoodResponse response=new FoodResponse(updatedFood);

        return response;
    }

    @Override
    public List<ResturantResponse> getResturantMenu(String foodname) {

        List<FoodItem> foodItems = foodRepository.findByFoodNameIgnoreCase(foodname);

        List<ResturantResponse> resturants = new ArrayList<>();
        if (foodItems.isEmpty()) {
            throw new FoodException("No food item found with name: " + foodname);
        } else {
            for (FoodItem food : foodItems) {
                Resturant resturant = food.getResturant();

                if (resturant.getActive()) {
                    LocalTime time = LocalTime.now();
                    boolean Open = !time.isBefore(resturant.getOpeningTime()) &&
                            !time.isAfter(resturant.getClosingTime());
                    ResturantResponse response = new ResturantResponse(resturant);
                    response.setIsOpen(Open);
                    resturants.add(response);
                }
            }
        }

        if (resturants.isEmpty()) {
            throw new ResturantException("No active resturant found with food item name: " + foodname);
        } else {
            return resturants;
        }
    }

    }


