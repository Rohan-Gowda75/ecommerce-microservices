package com.product.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.demo.dto.VariantValueDto;
import com.product.demo.request.AddVariantValueRequest;
import com.product.demo.response.ApiResponse;
import com.product.demo.service.VariantValueService;

import jakarta.validation.Valid;

@RestController
public class VariantValueController {
	
	@Autowired
	private VariantValueService vvservice;
	
	
	 @PostMapping("/add")
	public ResponseEntity<?> addVarientValue(@Valid @RequestBody AddVariantValueRequest request){
		VariantValueDto dto =vvservice.addVariantValue(request);
		return ResponseEntity.ok(new ApiResponse<>("Valrient value Added Succesfull", dto,HttpStatus.OK));
		
	}
	 
	 @GetMapping("/get/{productVariantId}")
	    public ResponseEntity<List<VariantValueDto>> getVAriants(@PathVariable Integer productVariantId){
	    	List<VariantValueDto> lvdto=vvservice.getVariantValueById(productVariantId);
	    	return new ResponseEntity(lvdto, HttpStatus.OK);
	    }
	

}
