package com.product.demo.exception;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(exception = AppException.class)
	    public ResponseEntity<?> handleAppException(AppException exception) {

	        return new ResponseEntity<>(
	                new com.product.demo.response.ApiResponse<>(
	                        exception.getMessage(),
	                        null,
	                        exception.getHttpStatus()
	                ),
	                exception.getHttpStatus()
	        );
	    }
	
	
	
	
	

}
