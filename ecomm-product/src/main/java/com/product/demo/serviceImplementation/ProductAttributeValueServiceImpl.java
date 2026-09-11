package com.product.demo.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.demo.dto.ProductAttributeDto;
import com.product.demo.entity.ProductAttribute;
import com.product.demo.exception.AppException;
import com.product.demo.repository.ProductAttributeRepo;
import com.product.demo.request.AddProductAttributeRequest;
import com.product.demo.service.ProductAttributeService;

@Service
public class ProductAttributeValueServiceImpl implements ProductAttributeService {

    @Autowired
    private ProductAttributeRepo parepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductAttributeDto addattribute(AddProductAttributeRequest request) {

        ProductAttribute pa = parepo
                .findByAttributeName(request.getAttributeName())
                .orElse(null);

        if (pa != null) {
            throw new AppException(
                    "Product Attribute Already Exists!",
                    HttpStatus.CONFLICT
            );
        }

        pa = new ProductAttribute();

        mapper.map(request, pa);

        pa = parepo.save(pa);

        return mapper.map(pa, ProductAttributeDto.class);
    }

    @Override
    public ProductAttributeDto updateAttribute(
            Integer attributeId,
            AddProductAttributeRequest request) {

        ProductAttribute ifExists = parepo.findById(attributeId)
                .orElseThrow(() -> new AppException(
                        "Product Attribute is Not Found!",
                        HttpStatus.NOT_FOUND
                ));

        mapper.map(request, ifExists);

        ifExists = parepo.save(ifExists);

        return mapper.map(ifExists, ProductAttributeDto.class);
    }

    @Override
    public List<ProductAttributeDto> getAllAttribute() {

        return parepo.findAll()
                .stream()
                .map(pa -> mapper.map(pa, ProductAttributeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductAttributeDto getByAttributeId(Integer attributeId) {

        ProductAttribute attribute = parepo.findById(attributeId)
                .orElseThrow(() -> new AppException(
                        "Attribute is Not Found!",
                        HttpStatus.NOT_FOUND
                ));

        return mapper.map(attribute, ProductAttributeDto.class);
    }

    @Override
    public void deleteAttribute(Integer attributeId) {

        ProductAttribute pa = parepo.findById(attributeId)
                .orElseThrow(() -> new AppException(
                        "Product Attribute Is Not Found!",
                        HttpStatus.NOT_FOUND
                ));

        parepo.deleteById(attributeId);
    }
}