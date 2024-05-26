package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Category;
import com.project.model.Product;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query("SELECT c from Category c where c.name = ?1")
	public Category findCategoryByName(String categoryName);
}
