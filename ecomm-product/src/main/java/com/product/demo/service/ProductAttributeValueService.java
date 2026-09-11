package com.product.demo.service;

import java.util.List;

import com.product.demo.dto.ProductAttributeValueDto;
import com.product.demo.request.AddValueNameRequest;

public interface ProductAttributeValueService {
	
ProductAttributeValueDto addattributeVAlue(AddValueNameRequest request);
	
	ProductAttributeValueDto updateattributeVAlue(Integer attributeValueId,AddValueNameRequest request);
	
	List<ProductAttributeValueDto> getAllValue();
	
	ProductAttributeValueDto getValueById(Integer attributeValueId);
	
	void deletevalueById(Integer attributeValueId);

}
