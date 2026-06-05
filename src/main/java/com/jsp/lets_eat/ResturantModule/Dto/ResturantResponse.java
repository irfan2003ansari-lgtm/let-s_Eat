package com.jsp.lets_eat.ResturantModule.Dto;

import com.jsp.lets_eat.ResturantModule.Model.Resturant;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ResturantResponse {

    private String ressturantName;
    private String description;
    private String address;
    private String city;
    private String state;
    private String country;
    private Boolean isOpen;
    private Boolean active;
    private LocalTime openingTime;
    private LocalTime closingTime;


    public ResturantResponse(Resturant resturant) {
        this.ressturantName = resturant.getRessturantName();
        this.description = resturant.getDescription();
        this.address = resturant.getAddress();
        this.city = resturant.getCity();
        this.state = resturant.getState();
        this.country = resturant.getCountry();
        this.active = resturant.getActive();
        this.openingTime = resturant.getOpeningTime();
        this.closingTime = resturant.getClosingTime();
    }
}
