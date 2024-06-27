package com.amazon.apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.apis.model.Product;
import com.amazon.apis.service.UserProductService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserProductService service;
	
	@GetMapping("/get-all")
	public ResponseEntity<List<Product>> getAllPoduct() {
		
		List<Product> list= null;
		list=service.getAllProduct();
		
		if (list!=null) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
	}
	
}
