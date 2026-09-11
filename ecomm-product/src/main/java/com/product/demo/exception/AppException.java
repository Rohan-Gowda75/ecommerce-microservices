package com.product.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppException extends RuntimeException {
	
	private HttpStatus httpstatus;
	
	public AppException(String message,HttpStatus httpstatus) {
		super(message);
		this.httpstatus = httpstatus;
	}
	
	public HttpStatus getHttpStatus() {
		return httpstatus;
	}

}
