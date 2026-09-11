package com.product.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariantValueDto {
	
	private Integer variantValueId;

    private Integer productVariantId;

    private Integer attributeValueId;

}
