package com.product.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.demo.entity.ProductAttributeValue;

@Repository
public interface ProductAttributeValueRepo extends JpaRepository<ProductAttributeValue,Integer> {
	
	Optional<ProductAttributeValue> findByValueName(String valueName);

}
