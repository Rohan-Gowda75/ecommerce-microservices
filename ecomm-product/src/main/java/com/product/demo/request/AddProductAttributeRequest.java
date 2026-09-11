package com.product.demo.request;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductAttributeRequest {
	
	@NotBlank(message = "Attribute Name is Required")
	private String attributeName;

}
