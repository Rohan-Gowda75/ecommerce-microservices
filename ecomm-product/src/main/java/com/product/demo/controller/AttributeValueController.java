package com.product.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.demo.dto.ProductAttributeDto;
import com.product.demo.dto.ProductAttributeValueDto;
import com.product.demo.request.AddProductAttributeRequest;
import com.product.demo.request.AddValueNameRequest;
import com.product.demo.response.ApiResponse;
import com.product.demo.service.ProductAttributeService;
import com.product.demo.service.ProductAttributeValueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/value")
public class AttributeValueController {
	
	@Autowired
	private ProductAttributeValueService pvrservice;
	
	@PostMapping("/addvalue")
	public ResponseEntity<?> addProductattributevalue(@Valid @RequestBody AddValueNameRequest request){
		ProductAttributeValueDto dto=pvrservice.addattributeVAlue(request);
		return ResponseEntity.ok(new ApiResponse<>("Attribute Value Added Successfully!",dto,HttpStatus.OK));
	}
	

	@PutMapping("/updatevalue/{attributeValueId}")
	public ResponseEntity<?> upadteProductAttributeValue(@PathVariable Integer attributeValueId,@Valid @RequestBody AddValueNameRequest request){
		ProductAttributeValueDto dto=pvrservice.updateattributeVAlue(attributeValueId, request);
		return ResponseEntity.ok(new ApiResponse<>("Atteribute Value Updated Successfully!", dto, HttpStatus.OK));
	}
	
	@DeleteMapping("/deletevalue/{attributeValueId}")
	public ResponseEntity<?> deleteProductattributevalue(@PathVariable Integer attributeValueId){
		pvrservice.deletevalueById(attributeValueId);
		return ResponseEntity.ok(new ApiResponse<>("Attribute Value deleted Successfully!", null, HttpStatus.OK));
	}
	
	@GetMapping("/retrive/{attributeValueId}")
	public ResponseEntity<?> getById(@PathVariable Integer attributeValueId){
		ProductAttributeValueDto dto=pvrservice.getValueById(attributeValueId);
		return ResponseEntity.ok(new ApiResponse<>("All Attribute Value!", dto, HttpStatus.OK));
	}
	
	@GetMapping("/gellallValue")
	public ResponseEntity<?> getAll(){
		List<ProductAttributeValueDto> dto=pvrservice.getAllValue();
		return ResponseEntity.ok(new ApiResponse<>("Attribute Value!",dto, HttpStatus.OK));
	}
}
