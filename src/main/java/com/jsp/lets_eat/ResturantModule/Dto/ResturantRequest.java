package com.jsp.lets_eat.ResturantModule.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ResturantRequest {
    @NotBlank(message = "Resturant name is required")
    private String ressturantName;
    private String description;
    private String address;
    private String city;
    private String state;
    private String country;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private String imageUrl;
    private Integer user_id;
}
