package com.eComm.user.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProfileDto {
	
	private Integer profileId;
	private String firstName;
	private String lastName;
	private LocalDate DOB;
	private String phoneNo;
	
	

}
