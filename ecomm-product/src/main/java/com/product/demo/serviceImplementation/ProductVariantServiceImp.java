package com.product.demo.serviceImplementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.demo.dto.ProductVariantDto;
import com.product.demo.entity.Product;
import com.product.demo.entity.ProductVariant;
import com.product.demo.exception.AppException;
import com.product.demo.repository.ProductRepo;
import com.product.demo.repository.ProductVariantRepo;
import com.product.demo.request.AddProductVarientRequest;
import com.product.demo.request.UpdateProductVariantRequest;
import com.product.demo.request.UpdateStocksRequest;
import com.product.demo.service.ProductVariantService;

@Service
public class ProductVariantServiceImp implements ProductVariantService {
	
	@Autowired
	private ProductVariantRepo pvrepo;

	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ProductVariantDto addProductVarient(AddProductVarientRequest request) {
		Product product = productRepo.findById(request.getProductId()).orElseThrow(() -> new AppException("Product Not Found!", HttpStatus.NOT_FOUND));

		ProductVariant alreadyExists = pvrepo.findByProductCode(request.getProductCode()).orElse(null);

		if (alreadyExists != null) {
			throw new AppException("Product Code Already Exists!", HttpStatus.CONFLICT);
		}

		ProductVariant variant = mapper.map(request, ProductVariant.class);
		variant.setProducts(product);
		variant = pvrepo.save(variant);
		return mapper.map(variant, ProductVariantDto.class);
	}
	

	@Override
	public ProductVariantDto getProductVariantById(String productCode) {
		ProductVariant variant = pvrepo.findByProductCode(productCode).orElseThrow(() -> new AppException("Product Variant Not Found!", HttpStatus.NOT_FOUND));
		return mapper.map(variant, ProductVariantDto.class);
	}

	@Override
	public ProductVariantDto updateProductVariant(Integer productVariantId, UpdateProductVariantRequest request) {
		ProductVariant variant = pvrepo.findById(productVariantId).orElseThrow(() -> new AppException("Product Variant Not Found!", HttpStatus.NOT_FOUND));

		if (request.getProductCode() != null && !request.getProductCode().equals(variant.getProductCode())) {
			ProductVariant alreadyExists = pvrepo.findByProductCode(request.getProductCode()).orElse(null);

			if (alreadyExists != null) {
				throw new AppException("Product Code Already Exists!", HttpStatus.CONFLICT);
			}
		}
		mapper.map(request, variant);
		variant = pvrepo.save(variant);
		return mapper.map(variant, ProductVariantDto.class);
	}

	@Override
	public ProductVariantDto addStocks(String productCode, UpdateStocksRequest request) {
		
	ProductVariant variant = pvrepo.findByProductCode(productCode).orElseThrow(() -> new AppException("Product Variant Not Found!", HttpStatus.NOT_FOUND));
		
		if (request.getStocks() == null || request.getStocks() <= 0) {
			throw new AppException("Stocks Must Be Greater Than Zero!", HttpStatus.BAD_REQUEST);
		}

		Integer currentStocks = variant.getStocks() + request.getStocks();
		variant.setStocks(currentStocks);
		variant = pvrepo.save(variant);
		return mapper.map(variant, ProductVariantDto.class);
	}

}
