package com.product.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.demo.dto.ProductDto;
import com.product.demo.request.ProductRequest;
import com.product.demo.request.UpdateProductRequest;
import com.product.demo.response.ApiResponse;
import com.product.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService pservice;

    @PostMapping( path = "/addProduct",consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //to tell json data consists String and Image data multitype data 
    public ResponseEntity<?> addProduct(
            @RequestParam String productName,  //when the client sends individual parameters, usually as query parameters or form fields.
            @RequestParam Double price,
            @RequestParam String description,
            @RequestParam String brandName,
            @RequestParam String categoryName,
            @RequestPart List<MultipartFile> images) { //when the request is multipart/form-data and you want to access one particular part.

        ProductRequest request = new ProductRequest();

        request.setProductName(productName);
        request.setPrice(price);
        request.setDescription(description);
        request.setBrandName(brandName);
        request.setCategoryName(categoryName);

        ProductDto dto = pservice.addProduct(request, images);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Product Added Successfully!",
                        dto,
                        HttpStatus.OK
                )
        );
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Integer productId) {

        ProductDto dto = pservice.getProductById(productId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Product Retrieved Successfully!",
                        dto,
                        HttpStatus.OK
                )
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {

        List<ProductDto> dto = pservice.getAll();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "All Products Retrieved Successfully!",
                        dto,
                        HttpStatus.OK
                )
        );
    }

    @PutMapping(path = "/update/{productId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(
            @PathVariable Integer productId,
            @RequestParam String productName,
            @RequestParam Double price,
            @RequestParam String description,
            @RequestParam String brandName,
            @RequestParam String categoryName,
            @RequestPart List<MultipartFile> images) {

        UpdateProductRequest request = new UpdateProductRequest();

        request.setProductName(productName);
        request.setPrice(price);
        request.setDescription(description);
        request.setBrandName(brandName);
        request.setCategoryName(categoryName);

        ProductDto dto =
                pservice.updateProduct(productId, request, images);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Product Updated Successfully!",
                        dto,
                        HttpStatus.OK
                )
        );
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteByProductId(
            @PathVariable Integer productId) {

        pservice.deleteByProductId(productId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Product Deleted Successfully!",
                        null,
                        HttpStatus.OK
                )
        );
    }
}