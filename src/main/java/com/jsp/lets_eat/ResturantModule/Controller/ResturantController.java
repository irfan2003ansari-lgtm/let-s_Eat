package com.jsp.lets_eat.ResturantModule.Controller;

import com.jsp.lets_eat.ResturantModule.Dto.FoodResponse;
import com.jsp.lets_eat.ResturantModule.Dto.ResturantRequest;
import com.jsp.lets_eat.ResturantModule.Dto.ResturantResponse;
import com.jsp.lets_eat.ResturantModule.Service.ResturantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lets-eat/resturant")
@RequiredArgsConstructor
public class ResturantController {
    private final ResturantService resturantService;

    @Operation(description = "API to register a new resturant")
    @ApiResponse(description = "Returns the registered resturant details", responseCode = "201")
    @ApiResponse(description = "Bad request if the resturant details are invalid", responseCode = "400")
    @PostMapping("/register")
    public ResponseEntity<ResturantResponse> createRestaurant(@RequestBody ResturantRequest resturantRequest){
        ResturantResponse resturantResponse=resturantService.createRestaurant(resturantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(resturantResponse);
    }

    @Operation(description = "API to get resturant details by ID")
    @ApiResponse(description = "Returns the resturant details for the given ID", responseCode = "200")
    @ApiResponse(description = "Resturant not found for the given ID", responseCode = "404")
    @GetMapping("/resturantById")
    public ResponseEntity<ResturantResponse> getResturantById(@RequestParam Long id){
        ResturantResponse resturantResponse=resturantService.getResturantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(resturantResponse);
    }

    @Operation(description = "API to get resturant details by name")
    @ApiResponse(description = "Returns the resturant details for the given name", responseCode = "200")
    @ApiResponse(description = "Resturant not found for the given name", responseCode = "404")
    @GetMapping("/resturantByName")
    public ResponseEntity<List<ResturantResponse>> getResturantByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(resturantService.getResturantByName(name));
    }

    @Operation(description = "API to get all resturants")
    @ApiResponse(description = "Returns a list of all resturants", responseCode = "200")
    @ApiResponse(description = "No resturants found", responseCode = "404")
    @GetMapping("/resturantall")
    public ResponseEntity<List<ResturantResponse>> getAllResturant(){
        return ResponseEntity.status(HttpStatus.OK).body(resturantService.getAll());
    }

    @Operation(description = "API to get resturant details by location")
    @ApiResponse(description = "Returns the resturant details for the given location", responseCode = "200")
    @ApiResponse(description = "Resturant not found for the given location", responseCode = "404")
    @GetMapping("/resturantByLoc")
    public ResponseEntity<List<ResturantResponse>> getResturantByLoc(@RequestParam String city){
        return ResponseEntity.status(HttpStatus.OK).body(resturantService.getResturantByLoc(city));
    }

    @Operation(description = "API to update the manager of a resturant")
    @ApiResponse(description = "Returns the updated resturant details with the new manager", responseCode = "200")
    @ApiResponse(description = "Resturant not found for the given ID", responseCode = "404")
    @PatchMapping("/updateManager")
    public ResponseEntity<ResturantResponse> updateManager(@RequestParam Long resturantId,@RequestParam Integer userId ){
        return ResponseEntity.status(HttpStatus.OK).body(resturantService.updateManager(resturantId,userId));
    }

    @Operation(description = "API to get food items of a resturant by resturant ID")
    @ApiResponse(description = "Returns a list of food items for the given resturant ID", responseCode = "200")
    @ApiResponse(description = "Resturant not found for the given ID", responseCode = "404")
    @GetMapping("/foodItems")
    public ResponseEntity<List<FoodResponse>> getFoodItemsByResturantId(@RequestParam Long resturantId){
        return ResponseEntity.status(HttpStatus.OK).body(resturantService.getFoodItemsByResturantId(resturantId));
    }

}
