package org.tsp.eshopplum.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsp.eshopplum.entities.Product;
import org.tsp.eshopplum.services.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping(value = "/category/{categoryName}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String categoryName){
        //System.out.println("\n########################\n" + categoryName + "\n########################\n");
        List<Product> products = productService.findProductsByCategory(categoryName);
        return ResponseEntity.ok().body(products);

    }
}
