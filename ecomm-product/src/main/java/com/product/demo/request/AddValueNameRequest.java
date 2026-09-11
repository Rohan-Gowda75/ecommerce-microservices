package com.product.demo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddValueNameRequest {
	
	@NotBlank(message = "Please Add Attribute Name")
	private String attributeName;
	
	@NotBlank(message = "Please Add Attribute Value Name")
	private String valueName;

}
