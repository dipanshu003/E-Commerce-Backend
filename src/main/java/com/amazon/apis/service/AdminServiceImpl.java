package com.amazon.apis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.amazon.apis.dto.ProductDTO;
import com.amazon.apis.model.Product;
import com.amazon.apis.repo.ProductRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	ProductRepository proRepo;

	@Override
	public Product createProduct(Product product) {
		Product pro = new Product();
		pro.setProductDesc(product.getProductDesc());
		pro.setProductImage(product.getProductImage());
		pro.setProductName(product.getProductName());
		pro.setProductPrice(product.getProductPrice());
		pro.setProductQuantity(product.getProductQuantity());
		
		
		pro = proRepo.save(pro);

		return pro;
	}

	@Override
	public Product getProduct(int id) {
		Product product = proRepo.findById(id).orElse(null);
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = null;
		list = proRepo.findAll();
		return list;
	}

	@Override
	public Product updateProduct(Product product) {

		Product dbProduct = proRepo.findById(product.getProductId()).orElse(null);
		
		if (dbProduct==null) {
			return dbProduct;
		}

		dbProduct.setProductName(product.getProductName());
		dbProduct.setProductPrice(product.getProductPrice());
		dbProduct.setProductQuantity(product.getProductQuantity());
		dbProduct.setProductImage(product.getProductImage());
		dbProduct.setProductDesc(product.getProductDesc());

		dbProduct = proRepo.save(dbProduct);
		return dbProduct;
	}

	@Override
	public String deleteProduct(int id) {
		String str = null;
		proRepo.deleteById(id);
		str = "Deleted";
		return str;
	}

}
