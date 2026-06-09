package com.jsp.lets_eat.ResturantModule.Controller;

import com.jsp.lets_eat.ResturantModule.Dto.FoodRequest;
import com.jsp.lets_eat.ResturantModule.Dto.FoodResponse;
import com.jsp.lets_eat.ResturantModule.Dto.ResturantResponse;
import com.jsp.lets_eat.ResturantModule.Service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lets-eat/food")
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/addFood")
    public ResponseEntity<FoodResponse> createFoodItem(@RequestBody FoodRequest foodRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.createFoodItem(foodRequest));
    }

    @PatchMapping("/updateStock")
    public ResponseEntity<FoodResponse> updateStock(@RequestParam Long foodId, @RequestParam Integer newStock){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.updateStock(foodId, newStock));
    }

    @GetMapping("/resturantMenu")
    public ResponseEntity<List<ResturantResponse>> getResturantMenu(@RequestParam String foodname){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.getResturantMenu(foodname));
    }

}
