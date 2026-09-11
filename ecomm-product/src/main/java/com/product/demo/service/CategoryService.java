package com.product.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.product.demo.dto.CategoryDto;
import com.product.demo.request.CategoryRequest;
import com.product.demo.request.UpdateCategoryRequest;

public interface CategoryService {
	
	public CategoryDto addCategory(CategoryRequest request,MultipartFile image);
	
	public CategoryDto getByCategoryId(Integer categoryId);
	
	public List<CategoryDto> getAll();
	
	public CategoryDto updateCategory(Integer categoryId, UpdateCategoryRequest request, MultipartFile image);
	
	public void deleteById(Integer categoryId );
	
	
	

}
