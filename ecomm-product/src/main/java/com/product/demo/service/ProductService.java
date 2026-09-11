package com.product.demo.service;

import com.product.demo.dto.ProductDto;
import com.product.demo.request.ProductRequest;
import com.product.demo.request.UpdateProductRequest;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
	
   public ProductDto addProduct (ProductRequest request,List<MultipartFile> images);
	
   public ProductDto getProductById(Integer productId);
	
   public List<ProductDto> getAll();
	
   public ProductDto updateProduct(Integer productId, UpdateProductRequest request, List<MultipartFile> images);
	
   public void deleteByProductId(Integer iproductId);
	
	
	
	
	
	

}
