package com.product.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.demo.dto.ProductVariantDto;
import com.product.demo.request.AddProductVarientRequest;
import com.product.demo.request.UpdateProductVariantRequest;
import com.product.demo.request.UpdateStocksRequest;
import com.product.demo.response.ApiResponse;
import com.product.demo.service.ProductVariantService;

import jakarta.validation.Valid;

@RestController
public class ProductVariantController {
	
	@Autowired
	private ProductVariantService productVariantService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addProductVariant(@Valid @RequestBody AddProductVarientRequest request) {
		ProductVariantDto dto = productVariantService.addProductVarient(request);
		return ResponseEntity.ok(new ApiResponse<>("Product Variant Added Successfully!", dto, HttpStatus.OK));
	}
	
	@GetMapping("/getproduct/{productCode}")
	public ResponseEntity<?> getProductVariantById(@PathVariable String productCode) {
		ProductVariantDto dto = productVariantService.getProductVariantById(productCode);
		return ResponseEntity.ok(new ApiResponse<>("Your Selected Product Variant!", dto, HttpStatus.OK));
	}
	
	
	@PutMapping("/update/{productVariantId}")
	public ResponseEntity<?> updateProductVariant(@PathVariable Integer productVariantId,@Valid @RequestBody UpdateProductVariantRequest request) {
		ProductVariantDto dto = productVariantService.updateProductVariant(productVariantId, request);
		return ResponseEntity.ok(new ApiResponse<>("Product Variant Updated Successfully!", dto, HttpStatus.OK));
	}
	
	@PutMapping("/addStock/{productCode}")
	public ResponseEntity<?> addStocks(@PathVariable String productCode,@Valid @RequestBody UpdateStocksRequest request) {
		ProductVariantDto dto = productVariantService.addStocks(productCode, request);
		return ResponseEntity.ok(new ApiResponse<>("Stocks Added Successfully!", dto, HttpStatus.OK));
	}

	

}
