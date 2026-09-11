package com.product.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.demo.entity.ProductVariant;

@Repository
public interface ProductVariantRepo extends JpaRepository<ProductVariant, Integer> {

	Optional<ProductVariant> findByProductCode(String productCode);
	
	

}
