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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.product.demo.dto.CategoryDto;
import com.product.demo.request.CategoryRequest;
import com.product.demo.request.UpdateCategoryRequest;
import com.product.demo.response.ApiResponse;
import com.product.demo.service.CategoryService;


@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService cservice;
	
	
	@PostMapping(path="/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addCategory(@RequestParam String categoryName, @RequestParam String categoryDescription, @RequestPart ("image") MultipartFile image){
		CategoryRequest request = new CategoryRequest();
		request.setCategoryName(categoryName);
		request.setCategoryDescription(categoryDescription);
		CategoryDto dto = cservice.addCategory(request, image);
		
		return ResponseEntity.ok(new ApiResponse<>("Categotry addes succesfull",dto,HttpStatus.OK));
		
	}
	
	
	@GetMapping("/getbyId/{categoryId}")
	public ResponseEntity<?> getByCategoryId(@PathVariable Integer categoryId){
		CategoryDto dto = cservice.getByCategoryId(categoryId);
		return ResponseEntity.ok(new ApiResponse<>("CategoryRetrieved", dto, HttpStatus.OK));
		
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		List<CategoryDto> dto = cservice.getAll();
		return ResponseEntity.ok(new ApiResponse<>("Retrieved Succesfull", dto, HttpStatus.OK));
		
	}
	
	
	@PutMapping(path="/updateCategory/{categoryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateCategory(@PathVariable Integer categoryId, @RequestParam String categoryName,
			                               @RequestParam String categoryDescription,@RequestPart ("image") MultipartFile image){
		
		UpdateCategoryRequest request = new UpdateCategoryRequest();
		request.setCategoryName(categoryName);
		request.setCategoryDescription(categoryDescription);
		CategoryDto dto = cservice.updateCategory(categoryId, request, image);
		return ResponseEntity.ok(new ApiResponse<>("Updation Succesfull", dto, HttpStatus.OK));									
		
	}
	
	
	@DeleteMapping("/deletecategoryByid/{categoryId}")
	public ResponseEntity<?>  deleteById(@PathVariable Integer categoryId){
		cservice.deleteById(categoryId);
		
		return ResponseEntity.ok(new ApiResponse<>("Deletion Succesfull", null, HttpStatus.OK));
		
	}
	
	
	

}
