package com.company.jewelrystore.repository;

import com.company.jewelrystore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findAllByCategory(String category);
}
