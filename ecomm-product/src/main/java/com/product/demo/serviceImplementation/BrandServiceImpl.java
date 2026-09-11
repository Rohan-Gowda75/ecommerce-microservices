package com.product.demo.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.demo.dto.BrandDto;
import com.product.demo.entity.Brand;
import com.product.demo.exception.AppException;
import com.product.demo.repository.BrandRepo;
import com.product.demo.request.BrandRequest;
import com.product.demo.request.UpdateBrandRequest;
import com.product.demo.response.CloudinaryResponse;
import com.product.demo.service.BrandServive;
import com.product.demo.service.CloudinaryService;

@Service
public class BrandServiceImpl implements BrandServive {
	
	
	@Autowired
	private BrandRepo brepo;
	
	
	@Autowired
	private ModelMapper mapper;

	
	@Autowired
	private CloudinaryService cservice;


	@Override
	public BrandDto addBrand(BrandRequest request, MultipartFile image) {
		Brand alreadyExists = brepo.findByBrandName(request.getBrandName()).orElse(null);
		if(alreadyExists!=null) {
			throw new AppException("Brand AlreadyExists", HttpStatus.CONFLICT); 
		}
		 if(image==null || image.isEmpty()) {
			 throw new AppException("Brand Image Required", HttpStatus.CONFLICT);
		 }
		 
		 Brand b = new Brand();
		 b = mapper.map(request, Brand.class);
		 CloudinaryResponse response = cservice.uploadImage(image);
		 b.setPublicId(response.getPublicId());
		 b.setBrandImagePath(response.getImageUrl());
		 b=brepo.save(b);
		return mapper.map(b, BrandDto.class);
	}


	@Override
	public BrandDto getBrandByID(Integer brandId) {
		Brand b = brepo.findById(brandId).orElseThrow(()->new AppException("Brand Not Found!", HttpStatus.NOT_FOUND));
		return mapper.map(b, BrandDto.class);
	}


	@Override
	public List<BrandDto> getAll() {
		List<BrandDto> b = brepo.findAll().stream().map((bb)->mapper.map(bb, BrandDto.class)).collect(Collectors.toList());
		return b;
	}


	@Override
	public BrandDto updateBrand(Integer brandId, UpdateBrandRequest request, MultipartFile image) {
	    Brand brand = brepo.findById(brandId)
	            .orElseThrow(() -> new AppException("No Brand data Found", HttpStatus.NOT_FOUND));

	    Brand ifExists = brepo.findByBrandName(request.getBrandName()).orElse(null);

	    if (ifExists != null && !ifExists.getBrandId().equals(brandId)) {
	        throw new AppException("Brand Already Exists", HttpStatus.CONFLICT);
	    }

	    if (image != null && !image.isEmpty()) {

	        if (brand.getBrandImagePath() != null && brand.getPublicId() != null) {
	            cservice.deleteImage(brand.getPublicId());
	        }

	        CloudinaryResponse response = cservice.uploadImage(image);

	        brand.setBrandImagePath(response.getImageUrl());
	        brand.setPublicId(response.getPublicId());
	    }

	    mapper.map(request, brand);

	    brand = brepo.save(brand);

	    return mapper.map(brand, BrandDto.class);
	}


	@Override
	public void deleteByBrandId(Integer brandId) {
		Brand exists = brepo.findById(brandId).orElseThrow(()-> new AppException("Barnd Not found",HttpStatus.NOT_FOUND));
		if(exists.getPublicId()!=null) {
			cservice.deleteImage(exists.getPublicId());
			
		}
		
		brepo.deleteById(brandId);
		
	}
}
