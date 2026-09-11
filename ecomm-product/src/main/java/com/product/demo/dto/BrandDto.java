package com.product.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
	
	private Integer brandId;
	
	private Integer brandName;
	
	private String brandImagePath;

}
