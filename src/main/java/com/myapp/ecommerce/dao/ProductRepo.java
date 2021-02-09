package com.myapp.ecommerce.dao;

import com.myapp.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200")
public interface ProductRepo extends JpaRepository<Product, Long> {

    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable); // Similar to "SELECT * FROM product WHERE category_id=?"

    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable); // Similar to "SELECT * FROM product WHERE p.name LIKE CONCAT('%', :name, '%')"



}
