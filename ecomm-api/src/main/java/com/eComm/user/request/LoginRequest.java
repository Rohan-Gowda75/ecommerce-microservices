package com.eComm.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank(message = "Email cannot be Empty!")
    private String email;
     
	@NotBlank(message = "password cannot be Empty!")
    private String password;
}