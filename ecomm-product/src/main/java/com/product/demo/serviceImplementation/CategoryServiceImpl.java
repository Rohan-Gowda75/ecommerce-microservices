package com.product.demo.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.demo.dto.CategoryDto;
import com.product.demo.entity.Category;
import com.product.demo.exception.AppException;
import com.product.demo.repository.CategoryRepo;
import com.product.demo.request.CategoryRequest;
import com.product.demo.request.UpdateCategoryRequest;
import com.product.demo.response.CloudinaryResponse;
import com.product.demo.service.CategoryService;
import com.product.demo.service.CloudinaryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	
	@Autowired
	private CategoryRepo crepo;
	
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Autowired
	private CloudinaryService cservice;


	@Override
	public CategoryDto addCategory(CategoryRequest request, MultipartFile image) {
		Category ifExists = crepo.findByCategoryName(request.getCategoryName()).orElse(null);
		
		if(ifExists!=null) {
			throw new AppException("Category Already Exists", HttpStatus.CONFLICT);
		}
		
		if(image==null || image.isEmpty()) {
			throw new AppException("Category Image Required",HttpStatus.BAD_REQUEST);
		}
		
		Category c= new Category();
		c = mapper.map(request,Category.class);
		CloudinaryResponse response = cservice.uploadImage(image);
		c.setCategoryPublicId(response.getPublicId());
		c.setCategoryImageUrl(response.getImageUrl());
		c=crepo.save(c);
		return mapper.map(c, CategoryDto.class);
		
		
	}


	@Override
	public CategoryDto getByCategoryId(Integer categoryId) {
		Category ifExists = crepo.findById(categoryId).orElseThrow(()-> new AppException("no category found", HttpStatus.NOT_FOUND));
		
		return mapper.map(ifExists, CategoryDto.class);
	}


	@Override
	public List<CategoryDto> getAll() {
		return crepo.findAll().stream().map((c)-> mapper.map(c, CategoryDto.class)).collect(Collectors.toList());
		
		
	}


	@Override
	public CategoryDto updateCategory(Integer categoryId, UpdateCategoryRequest request, MultipartFile image) {
		Category present = crepo.findById(categoryId).orElseThrow(()-> new AppException("No category Found", HttpStatus.NOT_FOUND));
		Category ifExists=crepo.findByCategoryName(request.getCategoryName()).orElse(null);
		if(ifExists!=null && !ifExists.getCategoryId().equals(categoryId)) {
			throw new AppException("Category Already Exists!", HttpStatus.CONFLICT);
		}
		
		if(image!=null && !image.isEmpty()) {
			if(present.getCategoryImageUrl()!=null && present.getCategoryPublicId()!=null) {
				cservice.deleteImage(present.getCategoryPublicId());
			}
			CloudinaryResponse response = cservice.uploadImage(image);
			present.setCategoryImageUrl(response.getImageUrl());
			present.setCategoryPublicId(response.getPublicId());
		}
		mapper.map(request, present);
		present = crepo.save(present);
		return mapper.map(present, CategoryDto.class);
	}


	@Override
	public void deleteById(Integer categoryId) {
		Category ifExists = crepo.findById(categoryId).orElseThrow(()-> new AppException("No category to delete", HttpStatus.BAD_REQUEST));
		
		if(ifExists.getCategoryPublicId()!=null) {
			cservice.deleteImage(ifExists.getCategoryPublicId());
		}
		
		crepo.deleteById(categoryId);
		
	}
	
	

}
