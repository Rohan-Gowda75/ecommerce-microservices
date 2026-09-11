package com.product.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.product.demo.dto.BrandDto;
import com.product.demo.request.BrandRequest;
import com.product.demo.request.UpdateBrandRequest;

public interface BrandServive {
	
	public BrandDto addBrand(BrandRequest request, MultipartFile image);
	
	public BrandDto getBrandByID(Integer brandId);
	
	public List<BrandDto> getAll();
	
	public BrandDto updateBrand(Integer brandId, UpdateBrandRequest request,MultipartFile image);
	
	public void deleteByBrandId(Integer brandId);
	
	
	
	

}
