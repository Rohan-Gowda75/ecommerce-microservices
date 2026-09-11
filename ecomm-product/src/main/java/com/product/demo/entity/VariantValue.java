package com.product.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VariantValue {
	
	@Id
	@GeneratedValue
	(strategy = GenerationType.IDENTITY)
	private Integer variantValueId;
	
	@ManyToOne
	@JoinColumn(name="productVariantId")
	private ProductVariant productVariant;
	
	@ManyToOne
	@JoinColumn(name="attributeValueId")
	private ProductAttributeValue productattributevalue;

}
