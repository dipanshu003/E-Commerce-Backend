package com.amazon.apis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.apis.model.Product;
import com.amazon.apis.repo.ProductRepository;

@Service
public class UserProductServiceImpl implements UserProductService {

	
	@Autowired
	private ProductRepository proRepo;

	@Override
	public List<Product> getAllProduct() {
		List<Product> list= null;
		
		list=proRepo.findAll();
		return list;
	}
}
