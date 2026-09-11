package com.product.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttribute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attributeId;
	
	@Column(unique = true)
	private String attributeName;

}
