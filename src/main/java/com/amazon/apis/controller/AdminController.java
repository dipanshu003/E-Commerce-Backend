package com.amazon.apis.controller;

import com.amazon.apis.model.ImageModel;
import com.amazon.apis.model.Product;
import com.amazon.apis.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProduct(@RequestPart("product") Product product,
                                                 @RequestPart("imageFile") MultipartFile[] files) {
        try {
            Set<ImageModel> images = uploadImage(files);
            product.setProductImage(images);
            Product createdProduct = service.createProduct(product);
            return ResponseEntity.ok(createdProduct);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestPart("product") Product product,
                                                 @RequestPart("imageFile") MultipartFile[] files) {
        try {
            Set<ImageModel> images = uploadImage(files);
            product.setProductImage(images);
            Product updatedProduct = service.updateProduct(product);
            return ResponseEntity.ok(updatedProduct);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = service.getProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(product);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> productList = service.getAllProduct();
        if (productList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(productList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        String deletionStatus = service.deleteProduct(id);
        if (deletionStatus == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletionStatus);
    }

    private Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            imageModels.add(imageModel);
            break;
        }
        return imageModels;
    }
}
