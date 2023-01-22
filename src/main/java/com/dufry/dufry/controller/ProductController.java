package com.dufry.dufry.controller;

import com.dufry.dufry.entity.Product;
import com.dufry.dufry.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.ProtectionDomain;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createProducts")
    public Product createProduct(@Validated @RequestBody Product product) {
        return  productService.createProduct(product);
    }

    @RequestMapping("/listofProducts")
    public List<Product> listProduct() {
        return productService.listOfProducts();
    }

    @PutMapping("/updateProduct/{id}")
    public Product updateProduct(@PathVariable Long id ,@RequestBody Product product) {
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
