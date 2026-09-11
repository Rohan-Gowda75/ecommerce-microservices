package com.product.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	
private Integer categoryId;
	
	private String categoryName;
	
	private String categoryDescription;
	
	private String categoryImageUrl;

}
