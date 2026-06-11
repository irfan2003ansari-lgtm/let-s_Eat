package com.jsp.lets_eat.CommonModule.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponseStructure> handleUserNotFoundException(UserException ex) {
        ErrorResponseStructure errorResponse = new ErrorResponseStructure();
        errorResponse.setStatuscode(404);
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ResturantException.class)
    public ResponseEntity<ErrorResponseStructure> handleResturantNotFoundException(ResturantException ex) {
        ErrorResponseStructure errorResponse = new ErrorResponseStructure();
        errorResponse.setStatuscode(404);
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handle(MethodArgumentNotValidException e) {

        List<ObjectError> allErrors = e.getAllErrors();
        Map<String, String> map = new HashMap<>();
        for (ObjectError o : allErrors) {
            FieldError f = (FieldError) o;
            map.put(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseStructure> handleException(Exception ex) {
        ErrorResponseStructure errorResponse = new ErrorResponseStructure();
        errorResponse.setStatuscode(500);
        errorResponse.setMessage("An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(FoodException.class)
    public ResponseEntity<ErrorResponseStructure> handleFoodException(FoodException ex) {
        ErrorResponseStructure errorResponse = new ErrorResponseStructure();
        errorResponse.setStatuscode(404);
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ErrorResponseStructure> handleCartException(CartException ex) {
        ErrorResponseStructure errorResponse = new ErrorResponseStructure();
        errorResponse.setStatuscode(404);
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
