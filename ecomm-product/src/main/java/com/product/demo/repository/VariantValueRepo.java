package com.product.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.demo.entity.VariantValue;

@Repository
public interface VariantValueRepo extends JpaRepository<VariantValue, Integer>{
	 List<VariantValue> findByProductVariant_ProductVariantId(Integer productVariantId);

}
