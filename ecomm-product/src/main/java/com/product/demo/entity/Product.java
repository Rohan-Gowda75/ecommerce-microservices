package com.product.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	private String productName;
	
	private Double price;
	
	private String description;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate craetedAt;
	
	private LocalDateTime updatedAt;
	
	
	@ManyToOne
	@JoinColumn(name="brandId")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	
	
	private List<ProductImage> produtimage;
	
	@PreUpdate // it is a method  level annootation so we created the method
	public void setupdatedAt() {
		this.updatedAt=LocalDateTime.now();
	}
	
	

}




