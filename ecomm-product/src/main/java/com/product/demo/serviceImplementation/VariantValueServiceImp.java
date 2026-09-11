package com.product.demo.serviceImplementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.demo.dto.VariantValueDto;
import com.product.demo.entity.ProductAttributeValue;
import com.product.demo.entity.ProductVariant;
import com.product.demo.entity.VariantValue;
import com.product.demo.exception.AppException;
import com.product.demo.repository.ProductAttributeValueRepo;
import com.product.demo.repository.ProductVariantRepo;
import com.product.demo.repository.VariantValueRepo;
import com.product.demo.request.AddVariantValueRequest;
import com.product.demo.service.VariantValueService;

@Service
public class VariantValueServiceImp implements VariantValueService {
	
	 @Autowired
	    private VariantValueRepo vvrepo;

	    @Autowired
	    private ProductVariantRepo pvrepo;

	    @Autowired
	    private ProductAttributeValueRepo avrepo;

	    @Autowired
	    private ModelMapper mapper;

		@Override
		public VariantValueDto addVariantValue(AddVariantValueRequest request) {
			ProductVariant varient = pvrepo.findById(request.getProductVariantId()).orElseThrow(() -> new AppException("Product Variant Not Found!",HttpStatus.NOT_FOUND));
			
			VariantValue variantValue = null;
			for(String valuename:request.getValueName()) {
				ProductAttributeValue attributeValue=avrepo.findByValueName(valuename).orElseThrow(()->new AppException("Product Attribute Value Not Found!", HttpStatus.NOT_FOUND));
	        	variantValue=new VariantValue();
	        	variantValue.setProductVariant(varient);
	        	variantValue.setProductattributevalue(attributeValue);
	        	variantValue=vvrepo.save(variantValue);
			}
			VariantValueDto dto = new VariantValueDto();
	        dto.setVariantValueId(variantValue.getVariantValueId());
	        dto.setProductVariantId(variantValue.getProductVariant().getProductVariantId());
	        dto.setAttributeValueId(variantValue.getProductattributevalue().getAttributeValueId());
	        return dto;
			
			
		
		}

		@Override
		public List<VariantValueDto> getVariantValueById(Integer productVariantId) {
			List<VariantValue> variantValue=vvrepo.findByProductVariant_ProductVariantId(productVariantId);
			
			if(variantValue.isEmpty()) {
				throw new AppException("Variant Values Not Found!", HttpStatus.NOT_FOUND);
			}
			return variantValue.stream().map(value->{
				VariantValueDto vvdto=new VariantValueDto();
				vvdto.setVariantValueId(value.getVariantValueId());
				vvdto.setAttributeValueId(value.getProductattributevalue().getAttributeValueId());
				vvdto.setProductVariantId(value.getProductVariant().getProductVariantId());
				return vvdto;
			}).toList();
		};
		}


