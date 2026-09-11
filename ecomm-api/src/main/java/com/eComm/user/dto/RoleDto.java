package com.eComm.user.dto;

import com.eComm.role.enums.RoleType;

import lombok.Data;


@Data
public class RoleDto {
	
private Integer roleId;
	
private RoleType roleName;

}
