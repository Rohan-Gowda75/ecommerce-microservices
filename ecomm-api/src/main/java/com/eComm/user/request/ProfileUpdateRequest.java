package com.eComm.user.request;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class ProfileUpdateRequest {
	
	@NotBlank(message="firstName cannot be Empty")
	private String firstName;
	
	private String lastName;
	
	@NotNull(message="Date of Birth must be filled")
	@Past(message="Date of birth cannot be future Date")
	private LocalDate dob;
	
	@NotBlank(message = "phoneNo cannot be Empty!")
	@Length(min=10, max = 10)
	private String phone;

}
