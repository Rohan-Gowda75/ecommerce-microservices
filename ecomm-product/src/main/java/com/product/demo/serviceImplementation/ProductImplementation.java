package com.product.demo.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.product.demo.dto.ProductDto;
import com.product.demo.entity.Brand;
import com.product.demo.entity.Category;
import com.product.demo.entity.Product;
import com.product.demo.entity.ProductImage;
import com.product.demo.exception.AppException;
import com.product.demo.repository.BrandRepo;
import com.product.demo.repository.CategoryRepo;
import com.product.demo.repository.ProductImageRepo;
import com.product.demo.repository.ProductRepo;
import com.product.demo.request.ProductRequest;
import com.product.demo.request.UpdateProductRequest;
import com.product.demo.response.CloudinaryResponse;
import com.product.demo.service.CloudinaryService;
import com.product.demo.service.ProductService;

@Service
public class ProductImplementation implements ProductService {

    @Autowired
    private ProductRepo prepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BrandRepo brepo;

    @Autowired
    private CategoryRepo crepo;

    @Autowired
    private CloudinaryService cservice;

    @Autowired
    private ProductImageRepo pirepo;


    @Transactional
    @Override
    public ProductDto addProduct(ProductRequest request, List<MultipartFile> images) {

        Brand ifExists = brepo
                .findByBrandName(request.getBrandName())
                .orElseThrow(() ->
                        new AppException(
                                "Brand Is Not Found!",
                                HttpStatus.NOT_FOUND
                        )
                );

        Category alreadyExists = crepo
                .findByCategoryName(request.getCategoryName())
                .orElseThrow(() ->
                        new AppException(
                                "Category Is Not Found!",
                                HttpStatus.NOT_FOUND
                        )
                );

        Product p = mapper.map(request, Product.class);

        p.setBrand(ifExists);
        p.setCategory(alreadyExists);

        p = prepo.save(p);


        if (images != null && !images.isEmpty()) {

            for (MultipartFile image : images) {

                if (image == null || image.isEmpty()) {

                    throw new AppException(
                            "Image is Required!",
                            HttpStatus.BAD_REQUEST
                    );
                }

                CloudinaryResponse response =
                        cservice.uploadImage(image);

                ProductImage pi = new ProductImage();

                pi.setProductPublicId(
                        response.getPublicId()
                );

                pi.setProductImagePath(
                        response.getImageUrl()
                );

                pi.setProduct(p);

                pirepo.save(pi);
            }
        }

        return mapper.map(p, ProductDto.class);
    }
    
    
    @Override
    public ProductDto getProductById(Integer productId) {

        Product p = prepo.findById(productId)
                .orElseThrow(() ->
                        new AppException(
                                "Product Not Found!",
                                HttpStatus.NOT_FOUND
                        )
                );

        return mapper.map(p, ProductDto.class);
    }


    @Override
    public List<ProductDto> getAll() {

        return prepo.findAll()
                .stream()
                .map((p) ->
                        mapper.map(p, ProductDto.class))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public ProductDto updateProduct(Integer productId,UpdateProductRequest request,List<MultipartFile> images) {

        Product p = prepo.findById(productId)
                .orElseThrow(() ->
                        new AppException(
                                "Product Not Found!",
                                HttpStatus.NOT_FOUND
                        )
                );


        Brand ifExistsbrand = brepo
                .findByBrandName(request.getBrandName())
                .orElseThrow(() ->
                        new AppException(
                                "Brand Not Found!",
                                HttpStatus.NOT_FOUND
                        )
                );


        Category ifExistscate = crepo
                .findByCategoryName(request.getCategoryName())
                .orElseThrow(() ->
                        new AppException(
                                "Category Is Not Found!",
                                HttpStatus.NOT_FOUND
                        )
                );


        mapper.map(request, p);

        p.setBrand(ifExistsbrand);
        p.setCategory(ifExistscate);


        if (images != null && !images.isEmpty()) {

            List<ProductImage> productImages =  pirepo.findByProducts(p);

            for (ProductImage pi : productImages) {

                if (pi.getProductPublicId() != null) {
                    cservice.deleteImage(
                            pi.getProductPublicId()
                    );
                }

                pirepo.delete(pi);
            }


            for (MultipartFile image : images) {

                if (image == null || image.isEmpty()) {

                    throw new AppException(
                            "Image is Required!",
                            HttpStatus.BAD_REQUEST
                    );
                }

                CloudinaryResponse response =
                        cservice.uploadImage(image);

                ProductImage pimage =
                        new ProductImage();

                pimage.setProductPublicId(
                        response.getPublicId()
                );

                pimage.setProductImagePath(
                        response.getImageUrl()
                );

                pimage.setProduct(p);

                pirepo.save(pimage);
            }
        }


        p = prepo.save(p);

        return mapper.map(p, ProductDto.class);
    }


    @Transactional
    @Override
    public void deleteByProductId(Integer productId) {

        Product p = prepo.findById(productId)
                .orElseThrow(() ->
                        new AppException(
                                "Product Not Found!",
                                HttpStatus.NOT_FOUND
                        )
                );


        List<ProductImage> productImages =
                pirepo.findByProducts(p);


        for (ProductImage productImage : productImages) {

            if (productImage.getProductPublicId() != null) {

                cservice.deleteImage(
                        productImage.getProductPublicId()
                );
            }

            pirepo.delete(productImage);
        }


        prepo.delete(p);
    }


   
}