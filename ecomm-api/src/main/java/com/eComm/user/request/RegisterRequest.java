package com.eComm.user.request;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.eComm.role.enums.RoleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class RegisterRequest {

	

	@NotBlank(message = "Email cannot be Empty!")
    private String email;

	@Length(min=6, max=12,  message = "Password must be between 6 and 12 characters")
	@NotBlank(message = "password cannot be Empty!")
    private String password;
    
	@NotBlank(message = "firstNAme cannot be Empty!")
    private String firstName;
    
	
    private String lastName;
    
	@NotNull(message = "Date od Birth Cannot be Empty!")
	@Past(message="Date of Birth cannot be future date")
    private LocalDate dob;
    
	@NotBlank(message = "phoneNo cannot be Empty!")
	@Length(min=10, max = 10, message = "Phone number must be exactly 10 digits")
    private String phoneNo;
    
    private  RoleType roleName;
    
    
}