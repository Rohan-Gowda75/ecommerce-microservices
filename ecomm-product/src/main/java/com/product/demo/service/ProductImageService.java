package com.product.demo.service;



import org.springframework.web.multipart.MultipartFile;

import com.product.demo.dto.ProductImageDto;
import com.product.demo.request.ProductImageRequest;

public interface ProductImageService {
	
	ProductImageDto addProductImage(ProductImageRequest request, MultipartFile image);
	
	ProductImageDto updateProductImage(Integer imageId, MultipartFile image);
	
	
	
	

}
