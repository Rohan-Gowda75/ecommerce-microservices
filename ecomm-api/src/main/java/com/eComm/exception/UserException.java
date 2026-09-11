package com.eComm.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserException extends RuntimeException{
	private HttpStatus httpStatus;
	
	public UserException(String message,HttpStatus httpStatus) {
		super(message);
		this.httpStatus=httpStatus;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}


