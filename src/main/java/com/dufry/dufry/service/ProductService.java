package com.dufry.dufry.service;

import com.dufry.dufry.entity.Product;

import java.util.List;

public interface ProductService {
     List<Product> listOfProducts();
     Product createProduct(Product product);
     void deleteProduct(Long skuId);
     Product updateProduct(Long skuId,Product product);
}
