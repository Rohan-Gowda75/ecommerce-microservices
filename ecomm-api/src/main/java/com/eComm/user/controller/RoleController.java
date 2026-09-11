package com.eComm.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eComm.exception.UserException;
import com.eComm.user.dto.RoleDto;
import com.eComm.user.request.AddRoleRequest;
import com.eComm.user.response.ApiResponse;
import com.eComm.user.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleservice;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addRole(@Validated @RequestBody AddRoleRequest request, BindingResult result ){
		
		if(result.hasErrors()) {
			throw new UserException(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		RoleDto dto = roleservice.addRole(request.getRoleName());
		
		return  ResponseEntity.ok(new ApiResponse<>("Role added successfully!",dto,HttpStatus.OK));
		
	}
	
	

}
