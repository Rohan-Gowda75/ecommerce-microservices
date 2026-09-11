package com.eComm.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eComm.exception.UserException;
import com.eComm.user.dto.UserDto;
import com.eComm.user.request.LoginRequest;
import com.eComm.user.request.RegisterRequest;
import com.eComm.user.request.UpdateRequest;
import com.eComm.user.response.ApiResponse;
import com.eComm.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class Controller {
	
	@Autowired
	private UserService uservice;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest request, BindingResult result){ // @RequestBody RegisterRequest request=Take incoming JSON data → convert into Java object
		
		
		if(result.hasErrors()) {
			throw new UserException(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		UserDto dto = uservice.register(request);
		
		return new ResponseEntity<>(
				new ApiResponse<>("Data added successfully", dto, HttpStatus.OK),
				HttpStatus.OK
		);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login( @Validated  @RequestBody LoginRequest request, BindingResult result){
		
		if(result.hasErrors()) {
			throw new UserException(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		UserDto dto = uservice.login(request);
				
		return new ResponseEntity<>(
				new ApiResponse<>("Login Succesfull",dto,HttpStatus.OK),
				HttpStatus.OK
				
				);
	}
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Integer userId){
		
		UserDto dto = uservice.getById(userId);
		
		return new ResponseEntity<>(
				new ApiResponse<>("Data by ID",dto,HttpStatus.OK),
				HttpStatus.OK
				);			
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getAllUser(){
		
	List<UserDto> dto = uservice.getAll();
	
	return new ResponseEntity<>(
			new ApiResponse<>("Data of all the user",dto,HttpStatus.OK),
			HttpStatus.OK);
		
		
		
	}
	
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateUserById(
			@Validated
			@RequestBody UpdateRequest request, 
			@PathVariable Integer userId,
			BindingResult result){
		
		if(result.hasErrors()) {
			throw new UserException(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		uservice.updateUser(userId, request);
		
		return new ResponseEntity<>(
				new ApiResponse<>("Data is succesfullly updated", null ,HttpStatus.OK),
				HttpStatus.OK);
				
		
		
		
	}
	
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable Integer userId){
		
		
		
		return null;
	}

}