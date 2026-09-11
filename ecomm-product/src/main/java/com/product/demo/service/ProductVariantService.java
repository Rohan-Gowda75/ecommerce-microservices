package com.product.demo.service;

import com.product.demo.dto.ProductVariantDto;
import com.product.demo.request.AddProductVarientRequest;
import com.product.demo.request.UpdateProductVariantRequest;
import com.product.demo.request.UpdateStocksRequest;

public interface ProductVariantService {
	
	ProductVariantDto addProductVarient(AddProductVarientRequest request);

	ProductVariantDto getProductVariantById(String productCode);

	ProductVariantDto updateProductVariant(Integer productVariantId, UpdateProductVariantRequest request);

	ProductVariantDto addStocks(String productCode, UpdateStocksRequest request);

}
