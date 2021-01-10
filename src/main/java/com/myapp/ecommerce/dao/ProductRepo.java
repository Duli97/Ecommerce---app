package com.myapp.ecommerce.dao;

import com.myapp.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ProductRepo extends JpaRepository<Product, Long> {


}
