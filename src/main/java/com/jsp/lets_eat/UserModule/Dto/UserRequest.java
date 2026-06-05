package com.jsp.lets_eat.UserModule.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private Long phone;
    private String email;
    private String password;
}


