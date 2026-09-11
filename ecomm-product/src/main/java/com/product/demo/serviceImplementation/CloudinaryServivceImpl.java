package com.product.demo.serviceImplementation;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.product.demo.exception.AppException;
import com.product.demo.response.CloudinaryResponse;
import com.product.demo.service.CloudinaryService;

@Service
public class CloudinaryServivceImpl implements CloudinaryService {

    @Autowired
    private Cloudinary cloud;

    @Override
    public CloudinaryResponse uploadImage(MultipartFile image) {

        CloudinaryResponse response = null;

        try {
            if (image != null && !image.isEmpty()) {

                Map<?, ?> cloudResult = cloud.uploader()
                        .upload(image.getBytes(), ObjectUtils.emptyMap());

                String publicId = cloudResult.get("public_id").toString();
                String imageUrl = cloudResult.get("secure_url").toString();

                response = new CloudinaryResponse(imageUrl, publicId);
            }

        } catch (IOException e) {
            throw new AppException(
                    "Image Upload Failed!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return response;
    }

    @Override
    public void deleteImage(String publicId) {

        try {

            Map<?, ?> deleteResult = cloud.uploader()
                    .destroy(publicId, ObjectUtils.emptyMap());

            String result = deleteResult.get("result").toString();

            if (!result.equalsIgnoreCase("ok")) {
                throw new AppException(
                        "An Error Occurred While Deleting Image",
                        HttpStatus.BAD_REQUEST
                );
            }

        } catch (IOException e) {
            throw new AppException(
                    "An Error Occurred While Deleting Image",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}