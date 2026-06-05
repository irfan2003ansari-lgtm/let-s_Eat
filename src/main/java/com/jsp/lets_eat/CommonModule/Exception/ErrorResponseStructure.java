package com.jsp.lets_eat.CommonModule.Exception;

import lombok.Data;

@Data
public class ErrorResponseStructure {

    private Integer statuscode;
    private String message;
}
