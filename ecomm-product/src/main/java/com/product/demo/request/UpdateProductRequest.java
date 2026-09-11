package com.product.demo.request;

import lombok.Data;

@Data
public class UpdateProductRequest {
	
private String productName;
	
	private Double price;
	
	private String description;
	
	private String brandName;
	
	private String categoryName;


}
