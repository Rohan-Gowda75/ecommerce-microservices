package com.product.demo.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductVarientRequest {
	
	@NotNull(message = "Product Id is required")
    private Integer productId;

	@NotNull(message= "Please Add Price For This Product Variant")
	@Positive(message = "Product price must be greater than 0")
    private Double price;

	@NotBlank(message = "Please Enter the Product Code")
    private String productCode;

	@Min(value = 1, message = "Stock must be at least 1")
    private Integer stocks;

}
