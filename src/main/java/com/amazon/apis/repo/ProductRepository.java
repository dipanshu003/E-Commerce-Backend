package com.amazon.apis.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.apis.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	
}
