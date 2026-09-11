package com.eComm.user.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.eComm.exception.UserException;
import com.eComm.user.response.CloudinaryResponse;
import com.eComm.user.service.CloudinaryService;


@Service
public class CloudinaryServiceImpl implements CloudinaryService {


    @Autowired
    private Cloudinary cloudinary; //bean class from config as ModelMapper mapper 


    @Override
    public CloudinaryResponse uploadImage(MultipartFile image) { // MultipartFile is a Spring interface used to handle uploaded files.

        CloudinaryResponse response = null;

        try { //This is not a normal DB operation,This is a network/API call to Cloudinary server so we have to put under Try Catch

            if(image != null && !image.isEmpty()) {


                Map<?, ?> result= cloudinary.uploader()
                        .upload(image, ObjectUtils.emptyMap());


                String publicId = result
                        .get("public_id")
                        .toString();


                String imageUrl = result
                        .get("secure_url")
                        .toString();


                response = new CloudinaryResponse(imageUrl, publicId);

            }


        } catch (IOException e) {

            throw new UserException(
                    "Image upload failed",
                    HttpStatus.BAD_REQUEST
            );

        }


        return response;
    }



    @Override
    public void deleteImage(String publicId) {


        try {

            Map<?, ?> result = cloudinary.uploader()
                    .destroy(publicId, ObjectUtils.emptyMap());


            String status = result
                    .get("result")
                    .toString();


            if(!status.equalsIgnoreCase("ok")) {

                throw new UserException(
                        "Image deletion failed",
                        HttpStatus.BAD_REQUEST
                );

            }


        } catch (IOException e) {


            throw new UserException(
                    "Image deletion failed",
                    HttpStatus.BAD_REQUEST
            );

        }

    }

}