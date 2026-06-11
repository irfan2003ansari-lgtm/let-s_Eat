package com.jsp.lets_eat.ResturantModule.Controller;

import com.jsp.lets_eat.ResturantModule.Dto.FoodRequest;
import com.jsp.lets_eat.ResturantModule.Dto.FoodResponse;
import com.jsp.lets_eat.ResturantModule.Dto.ResturantResponse;
import com.jsp.lets_eat.ResturantModule.Service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(description = "API to add a new food item to a restaurant's menu")
    @ApiResponse(description = "Returns the details of the created food item", responseCode = "201")
    @ApiResponse(description = "Bad request if the food item details are invalid", responseCode = "400")
    @PostMapping("/addFood")
    public ResponseEntity<FoodResponse> createFoodItem(@RequestBody FoodRequest foodRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.createFoodItem(foodRequest));
    }

    @Operation(description = "API to update the stock of a food item")
    @ApiResponse(description = "Returns the details of the updated food item", responseCode = "200")
    @ApiResponse(description = "Food item not found for the given ID", responseCode = "404")
    @PatchMapping("/updateStock")
    public ResponseEntity<FoodResponse> updateStock(@RequestParam Long foodId, @RequestParam Integer newStock){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.updateStock(foodId, newStock));
    }

    @Operation(description = "API to get the menu of restaurants that serve a specific food item")
    @ApiResponse(description = "Returns a list of restaurants that serve the specified food item", responseCode = "200")
    @ApiResponse(description = "No restaurants found that serve the specified food item", responseCode = "404")
    @GetMapping("/resturantMenu")
    public ResponseEntity<List<ResturantResponse>> getResturantMenu(@RequestParam String foodname){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.getResturantMenu(foodname));
    }

}
