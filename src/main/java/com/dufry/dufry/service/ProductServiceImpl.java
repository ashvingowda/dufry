package com.dufry.dufry.service;

import com.dufry.dufry.entity.Product;
import com.dufry.dufry.exceptions.ResourceNotFoundException;
import com.dufry.dufry.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listOfProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        product.setCreated(LocalDateTime.now());
        return productRepository.save(product);

    }

    @Override
    public void deleteProduct(Long skuId) {
         productRepository.deleteById(skuId);
    }

    @Override
    public Product updateProduct(Long skuId,Product product) {
        Product dbProdduct = productRepository.findById(skuId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
        if(product.getName()!=null || product.getName().length() == 0)
         dbProdduct.setName(product.getName());

        if(product.getName().length() == 0)
            dbProdduct.setName(product.getName());

        productRepository.save(dbProdduct);

        return  dbProdduct;

    }
}
