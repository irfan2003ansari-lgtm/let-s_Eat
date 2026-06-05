package com.jsp.lets_eat.UserModule.Controller;

import com.jsp.lets_eat.UserModule.Dto.LoginRequest;
import com.jsp.lets_eat.UserModule.Dto.USerResponse;
import com.jsp.lets_eat.UserModule.Dto.UserRequest;
import com.jsp.lets_eat.UserModule.Service.Implementation.UserServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lets-eat/user")
public class UserController {
   private final UserServiceImp userServiceImp;

   @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Operation(description = "API to save a new user")
    @ApiResponse(description = "Returns the saved user details", responseCode = "201")
    @ApiResponse(description = "Bad request if the user details are invalid", responseCode = "400")
    @PostMapping("/register")
    public ResponseEntity<USerResponse> savuser(@RequestBody UserRequest user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImp.register(user));
    }

    @Operation(description = "API to get user details by ID")
    @ApiResponse(description = "Returns the user details for the given ID", responseCode = "200")
    @ApiResponse(description = "User not found for the given ID", responseCode = "404")
    @GetMapping("/profile")
    public ResponseEntity<USerResponse> profile(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImp.profile(id));
    }

    @Operation(description = "API to login a user")
    @ApiResponse(description = "Returns the user details if login is successful", responseCode = "200")
    @ApiResponse(description = "User not found for the given email or incorrect password", responseCode = "404")
    @PostMapping("/login")
    public ResponseEntity<USerResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImp.login(loginRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Integer id){
       userServiceImp.delete(id);

       return ResponseEntity.status(HttpStatus.OK).body("User with id "+id+" deleted successfully");
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestParam String email,@RequestParam String oldPassword,@RequestParam String newPassword){
        userServiceImp.updatePassword(email,oldPassword,newPassword);
        return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully for email: " + email);
    }
}

