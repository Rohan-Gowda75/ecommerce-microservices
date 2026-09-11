package com.product.demo.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddVariantValueRequest {
	@NotNull(message = "Please Add The Product Variant Id")
    private Integer productVariantId;

    private List<String> valueName;

}
