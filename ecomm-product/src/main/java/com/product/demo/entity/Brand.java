package com.product.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer brandId;
	
	
	@Column(unique = true)
	private String brandName;
	
   private String brandImagePath;
	
	private String publicId;
	
	
	@OneToMany(mappedBy = "brand")
	private List<Product> products;
	
	

}
