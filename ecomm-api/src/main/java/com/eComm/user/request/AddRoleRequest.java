package com.eComm.user.request;

import com.eComm.role.enums.RoleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AddRoleRequest {
	
	@NotNull(message="Role Cannot be Blank")
	private RoleType roleName;
	

}
