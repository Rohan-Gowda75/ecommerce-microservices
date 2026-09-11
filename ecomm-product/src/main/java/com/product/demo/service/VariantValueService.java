package com.product.demo.service;

import java.util.List;

import com.product.demo.dto.VariantValueDto;
import com.product.demo.request.AddVariantValueRequest;

public interface VariantValueService {
	
	 VariantValueDto addVariantValue(AddVariantValueRequest request);

	    List<VariantValueDto> getVariantValueById(Integer productVariantId);

}
