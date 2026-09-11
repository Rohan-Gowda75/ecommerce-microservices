package com.product.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.product.demo.dto.BrandDto;
import com.product.demo.request.BrandRequest;
import com.product.demo.request.UpdateBrandRequest;
import com.product.demo.response.ApiResponse;
import com.product.demo.service.BrandServive;

@RestController
@RequestMapping("/brand")
public class BrandController {
	
	@Autowired
	private BrandServive brandservice;
	
	
	
	@PostMapping(path="/addbrand",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addBrand(@RequestParam String brandName,@RequestPart("image") MultipartFile image){ //@requestBody is not used bcz of incoming data 
		                                                                                                     // is not in only JSOn format its multitype format 
		BrandRequest request = new BrandRequest();
		request.setBrandName(brandName);
		BrandDto dto = brandservice.addBrand(request, image);
		 return ResponseEntity.ok(new ApiResponse<>("Brand Added Succesfully",dto,HttpStatus.OK));
		
		
	}
	
	
	@GetMapping("/getbyId/{brandId}")
	public ResponseEntity<?> getBrandByID(@PathVariable Integer brandId){
		BrandDto dto = brandservice.getBrandByID(brandId);
		
		return ResponseEntity.ok(new ApiResponse<>("Brand Retrieved", dto, HttpStatus.OK));
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		List<BrandDto> dto = brandservice.getAll();
		return ResponseEntity.ok(new ApiResponse<>("Brand retrieves", dto, HttpStatus.OK));
		
	}
	
	
	@PutMapping(path="/updateBrand/{brandId}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateBrand(@PathVariable Integer brandId, @RequestParam String brandName, @RequestPart("image") MultipartFile image){
		UpdateBrandRequest request = new UpdateBrandRequest();
		request.setBrandName(brandName);
		BrandDto dto = brandservice.updateBrand(brandId, request, image);
		return ResponseEntity.ok(new ApiResponse<>("Updation Succesfull", dto, HttpStatus.OK));
		
	}
	
	
	
	@DeleteMapping("/delete/{brandId}")
	public ResponseEntity<?> deleteByBrandId(@PathVariable Integer brandId){
		brandservice.deleteByBrandId(brandId);
		return ResponseEntity.ok(new ApiResponse<>("deletion Succesfull", null, HttpStatus.OK));
		
		
	}
	

}
