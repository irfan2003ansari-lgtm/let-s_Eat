package com.jsp.lets_eat.CartModule.Controller;

import com.jsp.lets_eat.CartModule.Dto.CartItemRequest;
import com.jsp.lets_eat.CartModule.Dto.CartResponse;
import com.jsp.lets_eat.CartModule.Service.CartService;
import com.jsp.lets_eat.CartModule.Service.CartServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lets-eat/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(description = "Add a food item to the cart for a specific user. If the user doesn't have an existing cart, a new cart will be created. The total price of the cart will be updated accordingly.")
    @ApiResponse(description = "Cart item added successfully and cart total price updated.", responseCode = "201")
    @ApiResponse(description = "User not found with the provided ID.", responseCode = "404")
    @PostMapping("/add")
    public ResponseEntity<CartResponse> createCart(@RequestBody CartItemRequest cartItemRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(cartItemRequest));
    }

    @Operation(description = "Retrieve the cart details for a specific user, including the list of cart items and the total price.")
    @ApiResponse(description = "Cart details retrieved successfully.", responseCode = "200")
    @ApiResponse(description = "User not found with the provided ID.", responseCode = "404")
    @GetMapping("/userid")
    public ResponseEntity<CartResponse> findByUserId(@RequestParam Integer userId){
        return ResponseEntity.ok(cartService.findByUserId(userId));
    }

    @Operation(description = "Increase the quantity of a specific food item in the user's cart. The total price of the cart will be updated accordingly.")
    @ApiResponse(description = "Cart item quantity increased successfully and cart total price updated.", responseCode = "200")
    @ApiResponse(description = "User not found with the provided ID.", responseCode = "404")
    @ApiResponse(description = "Food item not found with the provided ID.", responseCode = "404")
    @PatchMapping("/increase")
    public ResponseEntity<CartResponse> increaseQuantity(@RequestParam Long foodItemId,@RequestParam Integer userId){
        return ResponseEntity.ok(cartService.increaseQuantity(foodItemId,userId));
    }

    @Operation(description = "Decrease the quantity of a specific food item in the user's cart. If the quantity reaches zero, the item will be removed from the cart. The total price of the cart will be updated accordingly.")
    @ApiResponse(description = "Cart item quantity decreased successfully and cart total price updated.", responseCode = "200")
    @ApiResponse(description = "User not found with the provided ID.", responseCode = "404")
    @ApiResponse(description = "Food item not found with the provided ID.", responseCode = "404")
    @PatchMapping("/decrease")
    public ResponseEntity<CartResponse> decreaseQuantity(@RequestParam Long foodItemId,@RequestParam Integer userId){
        return ResponseEntity.ok(cartService.decreaseQuantity(foodItemId,userId));
    }
}
