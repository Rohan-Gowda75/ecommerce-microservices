package com.eComm.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.eComm.user.response.CloudinaryResponse;

public interface CloudinaryService {

    CloudinaryResponse uploadImage(MultipartFile image);

    void deleteImage(String publicId);

}