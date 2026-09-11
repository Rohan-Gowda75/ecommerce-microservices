package com.product.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.product.demo.response.CloudinaryResponse;



public interface CloudinaryService {
	
	CloudinaryResponse uploadImage(MultipartFile image);

    void deleteImage(String publicId);


}
