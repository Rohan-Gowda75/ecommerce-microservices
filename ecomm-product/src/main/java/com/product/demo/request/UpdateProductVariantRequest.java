package com.product.demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductVariantRequest {
	@Positive(message="Product price must be greater than 0")
    private Double price;
	
	@NotBlank(message = "Please Add Product Code")
    private String productCode;

}
