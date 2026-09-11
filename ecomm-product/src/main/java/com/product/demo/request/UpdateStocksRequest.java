package com.product.demo.request;

import jakarta.validation.constraints.Min;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStocksRequest {
	
	@Min(value = 1, message = "Stock must be at least 1")
    private Integer stocks;

}
