package com.jsp.lets_eat.ResturantModule.Model;

import com.jsp.lets_eat.ResturantModule.Dto.ResturantRequest;
import com.jsp.lets_eat.UserModule.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resturant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "resturant_seq", sequenceName = "resturant_seq", allocationSize = 1 ,initialValue = 1000)
    private Long id;
    private String ressturantName;
    private String description;
    private String address;
    private String city;
    private String state;
    private String country;
    private Boolean active;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private String imageUrl;
    @OneToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    public Resturant(ResturantRequest resturantRequest) {
        this.ressturantName = resturantRequest.getRessturantName();
        this.description = resturantRequest.getDescription();
        this.address = resturantRequest.getAddress();
        this.city = resturantRequest.getCity();
        this.state = resturantRequest.getState();
        this.country = resturantRequest.getCountry();
        this.openingTime = resturantRequest.getOpeningTime();
        this.closingTime = resturantRequest.getClosingTime();

    }
}
