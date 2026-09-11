package com.product.demo.service;

import java.util.List;

import com.product.demo.dto.ProductAttributeDto;
import com.product.demo.request.AddProductAttributeRequest;

public interface ProductAttributeService {
ProductAttributeDto addattribute(AddProductAttributeRequest request);
	
	ProductAttributeDto updateAttribute(Integer attributeId,AddProductAttributeRequest request);
	
	List<ProductAttributeDto> getAllAttribute();
	
	ProductAttributeDto getByAttributeId(Integer attributeId);
	
	void deleteAttribute(Integer attributeId);
	
	

}
