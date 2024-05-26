package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Category;
import com.project.model.Product;
import com.project.request.CategoryRequest;
import com.project.request.ProductRequest;
import com.project.response.ApiResponse;
import com.project.response.CategoryResponse;
import com.project.service.CategoryService;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("")
	public ResponseEntity createNewCategory(@Validated @RequestBody CategoryRequest req) {
		String message = categoryService.addNewCategory(req);
		ApiResponse res = new ApiResponse();
		res.setMessage(message);
		res.setStatus(true);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
	
	@GetMapping("")
	public ResponseEntity getAllCategories(@RequestParam(defaultValue = "0",required = false) Integer page){
		
		CategoryResponse cr= categoryService.getAllCategories(page);
		return ResponseEntity.status(HttpStatus.OK).body(cr);
	}
	
	@PutMapping("/{di}")
	public ResponseEntity updateCategory(@Validated @RequestBody CategoryRequest c, @PathVariable(name = "di") Integer categoryId) {
		String message = categoryService.updateCategory(c, categoryId);
		ApiResponse res = new ApiResponse();
		res.setMessage(message);
		res.setStatus(true);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
	
	@DeleteMapping("/{di}")
	public ResponseEntity deleteCategory(@PathVariable(name = "di") Integer categoryId) {
		String message = categoryService.deleteCategory(categoryId);
		ApiResponse res = new ApiResponse();
		res.setMessage(message);
		res.setStatus(true);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping("/{di}")
	public ResponseEntity getCategoryById(@PathVariable(name = "di") Integer categoryId) {
		Category category =  categoryService.findCategoryById(categoryId);
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
}
