package com.product.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.product.demo.entity.ProductImage;

import lombok.Data;

@Data

public class ProductDto {
	
	private Integer productId;
	
	private String productName;
	
	private Double price;
	
	private String description;
	
	private LocalDate createdAt;
	
private BrandDto brand;
	
	private CategoryDto category;
	
	private List<ProductImage> productimages;
	

}
