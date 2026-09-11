package com.product.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.demo.entity.Product;
import com.product.demo.entity.ProductImage;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductImage, Integer> {
	List<ProductImage> findByProducts(Product product);

}
