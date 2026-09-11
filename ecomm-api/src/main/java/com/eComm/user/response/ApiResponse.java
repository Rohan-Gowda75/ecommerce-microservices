package com.eComm.user.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private String message;

    private T t;

    private HttpStatus httpStatus;
    
    //Api response wrapped around dto to send structure consistent response  

}