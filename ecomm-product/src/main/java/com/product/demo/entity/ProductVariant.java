package com.product.demo.entity;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productVariantId;
	
	private Double price;
	
	@Column(unique = true)
	private String productCode;
	
	private Integer stocks;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@PreUpdate
	public void setupdatedAt() {
		this.updatedAt=LocalDateTime.now();
	}
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product products;
	
	@OneToMany(mappedBy = "productVariant")
	private List<VariantValue> variantvalue;


}
