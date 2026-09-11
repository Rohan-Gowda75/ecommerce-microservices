package com.product.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CloudinaryResponse {
	
	private String imageUrl;
	private String publicId;

}
