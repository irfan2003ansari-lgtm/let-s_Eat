package com.jsp.lets_eat.UserModule.Dto;

import com.jsp.lets_eat.UserModule.Entity.User;
import lombok.Data;

@Data
public class USerResponse {

    private Integer id;
    private String name;
    private String email;
    private Long phone;

    public USerResponse(User user){
        this.id= user.getId();
        this.name= user.getName();
        this.email= user.getEmail();
        this.phone=user.getPhone();
    }
}
