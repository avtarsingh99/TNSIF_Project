package com.example.demo;


import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import exceptions.ProductNotFound;

@RestController

@CrossOrigin(origins="*")

public class ProductController {

	 @Autowired
	 private ProductService service;//reference variable
	 @Autowired
	 private ProductRepository repo;
	 
	 
	 // RESTful API methods for Retrieval operations
	 @GetMapping("/products")     
	 public List<Product> list() 
	 {
	 return service.listAll();
	 }
	 
	 
	 
	 @GetMapping("/products/{id}")
	 public ResponseEntity<Product> get(@PathVariable Integer id) 
	 {
	 try
	 {
	 Product product = service.get(id);
	 return new ResponseEntity<Product>(product, HttpStatus.OK);
	 } 

	 catch(ProductNotFound ex)
	 {
		
		 return new ResponseEntity<Product>( HttpStatus.NOT_FOUND);
	 }
	 }
	 
	 
	 
	 
	 //create or insert

	 @PostMapping("/product")
	 public void add(@RequestBody Product product) 
	 {
	 service.save(product);
	 }
	 
	 

	 @PutMapping("/product/{id}")
	 public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) 
	 {
	 try
	 {
	 Product existproduct = service.get(id);
	 service.save(product);
	 return new ResponseEntity<>(HttpStatus.OK);
	 } 
	 catch (NoSuchElementException e) 
	 {
	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 } 
	 }

	 @DeleteMapping("/product/{id}")
	 public void delete(@PathVariable Integer id) //extracting ID from the URL
	 {
	 service.delete(id);
	 }
}
