package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.exception.CategoryException;
import com.project.exception.ProductException;
import com.project.model.Category;
import com.project.model.Product;
import com.project.repository.CategoryRepository;
import com.project.request.CategoryRequest;
import com.project.response.CategoryResponse;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	
	public String addNewCategory(CategoryRequest c) {
		Category existingCategory = categoryRepo.findCategoryByName(c.getName());
		if(existingCategory != null)
    	throw new CategoryException("Category already exists with this name");
		Category category = new Category();
		category.setName(c.getName());
		categoryRepo.save(category);
		return "Category added successfully";
	}
	
	public CategoryResponse getAllCategories(Integer page){
		org.springframework.data.domain.Pageable  p = PageRequest.of(page, 2);
		Page<Category> pageCategories =  categoryRepo.findAll(p);
		List<Category> categories = pageCategories.getContent();
		
		CategoryResponse cr = new CategoryResponse();
		cr.setContent(categories);
		cr.setPageNumber(pageCategories.getNumber());
		cr.setPageSize(pageCategories.getSize());
		cr.setTotalPages(pageCategories.getTotalPages());
		cr.setTotalElements(pageCategories.getTotalElements());
		cr.setIslastPage(pageCategories.isLast());
		return cr;
	}
	
	public Category findCategoryByName(String name) {
			if(name=="")
				throw new CategoryException("Category name not received");
			Category category = categoryRepo.findCategoryByName(name);
			if(category == null)
				throw new CategoryException("Category does not exists with this name");
			return category;
	}
	
	public String updateCategory(CategoryRequest c, Integer categoryId) {
		Optional<Category> optCategory = categoryRepo.findById(categoryId);
		if(optCategory.isEmpty())
			throw new CategoryException("Invalid category id");
		Category category = optCategory.get();
		category.setName(c.getName());
		categoryRepo.saveAndFlush(category);
		return "Category updated successfully";
	}
	
	public String deleteCategory(Integer categoryId) {
		Optional<Category> optCategory = categoryRepo.findById(categoryId);
		if(optCategory.isEmpty())
			throw new CategoryException("Invalid category id");
		Category category = optCategory.get();
		categoryRepo.delete(category);
		 return "Category deleted successfully";
	}
	
	public Category findCategoryById(Integer categoryId) {
		if(categoryId==null)
			throw new CategoryException("Category id not received");
		Optional<Category> optCategory = categoryRepo.findById(categoryId);
		if(optCategory.isEmpty())
			throw new CategoryException("Category does not exists with this id");
		return optCategory.get();
    }
}
