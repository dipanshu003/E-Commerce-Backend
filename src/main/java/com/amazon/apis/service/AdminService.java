package com.amazon.apis.service;

import java.util.List;

import com.amazon.apis.dto.ProductDTO;
import com.amazon.apis.model.Product;

public interface AdminService {

	Product createProduct(Product product);

	Product getProduct(int id);

	List<Product> getAllProduct();

	Product updateProduct(Product product);

	String deleteProduct(int id);

	
	
}
