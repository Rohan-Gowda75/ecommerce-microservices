package com.product.demo.serviceImplementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.demo.dto.ProductImageDto;
import com.product.demo.entity.Product;
import com.product.demo.entity.ProductImage;
import com.product.demo.exception.AppException;
import com.product.demo.repository.ProductImageRepo;
import com.product.demo.repository.ProductRepo;
import com.product.demo.request.ProductImageRequest;
import com.product.demo.response.CloudinaryResponse;
import com.product.demo.service.CloudinaryService;
import com.product.demo.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepo pirepo;

    @Autowired
    private ProductRepo prepo;

    @Autowired
    private CloudinaryService cservice;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductImageDto addProductImage(ProductImageRequest request, MultipartFile image) {

        Product p = prepo.findById(request.getProductId())
                .orElseThrow(() -> new AppException(
                        "No product Found",
                        HttpStatus.NOT_FOUND));

        if (image == null || image.isEmpty()) {
            throw new AppException(
                    "Image Required",
                    HttpStatus.BAD_REQUEST);
        }

        CloudinaryResponse response = cservice.uploadImage(image);

        ProductImage productImage = new ProductImage();

        productImage.setProductImagePath(response.getImageUrl());
        productImage.setProductPublicId(response.getPublicId());
        productImage.setProduct(p);

        productImage = pirepo.save(productImage);

        return mapper.map(productImage, ProductImageDto.class);
    }

    @Override
    public ProductImageDto updateProductImage(Integer imageId, MultipartFile image) {

        ProductImage productImage = pirepo.findById(imageId)
                .orElseThrow(() -> new AppException(
                        "Image Not Found",
                        HttpStatus.NOT_FOUND));

        if (image != null && !image.isEmpty()) {

            if (productImage.getProductImagePath() != null
                    && productImage.getProductPublicId() != null) {

                cservice.deleteImage(productImage.getProductPublicId());
            }

            CloudinaryResponse response = cservice.uploadImage(image);

            productImage.setProductPublicId(response.getPublicId());
            productImage.setProductImagePath(response.getImageUrl());

            productImage = pirepo.save(productImage);
        }

        return mapper.map(productImage, ProductImageDto.class);
    }
}