package com.product.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.demo.dto.ProductAttributeDto;
import com.product.demo.request.AddProductAttributeRequest;
import com.product.demo.response.ApiResponse;
import com.product.demo.service.ProductAttributeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class ProductAttributeController {
	
	@Autowired
	private ProductAttributeService paservice;
	
	@PostMapping("/addattribute")
	public ResponseEntity<?> addProductAttribute(@Valid @RequestBody AddProductAttributeRequest request){
		ProductAttributeDto padto=paservice.addattribute(request);
		return ResponseEntity.ok(new ApiResponse<>("Product Atttribute Added Succesfully",padto,HttpStatus.OK));
		
	}
	
	@GetMapping("/updateattribute/{attributeId}")
	public ResponseEntity<?> updateProductAttribute(@PathVariable Integer attributeId, @Valid @RequestBody AddProductAttributeRequest request){
		ProductAttributeDto padto =paservice.updateAttribute(attributeId, request);
	return ResponseEntity.ok(new ApiResponse<>("Product Attribute Updated Succesfully!",padto,HttpStatus.OK)) ;
		
	}
	
	
	@GetMapping("/getallAttribute")
	public ResponseEntity<?> getAllattribute(){
		List<ProductAttributeDto> padto=paservice.getAllAttribute();
		return ResponseEntity.ok(new ApiResponse<>("Product Attribute!",padto,HttpStatus.OK));	
		
	}
	
	public ResponseEntity<?> deleteAttributes(@PathVariable Integer attributeId){
		paservice.deleteAttribute(attributeId);
		return ResponseEntity.ok(new ApiResponse<>("deletion Succesfull", null, HttpStatus.OK));
		
	}
	
	
	
	
	
	
	

}
