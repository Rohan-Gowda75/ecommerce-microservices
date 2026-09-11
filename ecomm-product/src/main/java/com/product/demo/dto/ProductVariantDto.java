package com.product.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantDto {
	
	private Integer productVariantId;

    private Integer productId;

    private Double price;

    private String productCode;

    private Integer stocks;
    
    private LocalDateTime createdAt;

}
