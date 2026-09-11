package com.product.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.demo.entity.ProductAttribute;

@Repository
public interface ProductAttributeRepo extends JpaRepository<ProductAttribute, Integer>{
	
	Optional<ProductAttribute> findByAttributeName(String attributeName);

}
