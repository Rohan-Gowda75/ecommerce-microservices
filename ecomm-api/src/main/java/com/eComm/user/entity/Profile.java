package com.eComm.user.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ProfileId;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String phoneNo;
	private LocalDate DOB;
    private String imageUrl;
	
	private String publicUrl;
	
	@OneToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	
	

}
