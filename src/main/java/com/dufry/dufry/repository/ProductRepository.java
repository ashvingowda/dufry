package com.dufry.dufry.repository;

import com.dufry.dufry.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
