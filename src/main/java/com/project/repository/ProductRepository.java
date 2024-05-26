package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Category;
import com.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT p from Product p where p.title = ?1")
	public Product findProductByName(String productName);
}
