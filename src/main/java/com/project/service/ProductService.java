package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.exception.CategoryException;
import com.project.exception.ProductException;
import com.project.model.Category;
import com.project.model.Product;
import com.project.repository.ProductRepository;
import com.project.request.ProductRequest;
import com.project.response.ProductResponse;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryService categoryService;
	
	public String addNewProduct(ProductRequest p) {
		Product existingProduct = productRepo.findProductByName(p.getTitle());
		    if(existingProduct != null)
		    	throw new ProductException("Product already exists with this name");
			Product product = new Product();
			product.setTitle(p.getTitle());
			product.setDescription(p.getDescription());
			product.setPrice(p.getPrice());
			Category category = categoryService.findCategoryByName(p.getCategory());
			if(category == null)
				throw new CategoryException("Catgory does not exist with this name");
			product.setCategory(category);
		 productRepo.save(product);
		 return "Product added successfully";
	}
	
	public ProductResponse getAllProducts(Integer page){
        org.springframework.data.domain.Pageable  p = PageRequest.of(page, 5);
		Page<Product> pageProducts =  productRepo.findAll(p);
		List<Product> products = pageProducts.getContent();
		
		ProductResponse pr = new ProductResponse();
		pr.setContent(products);
		pr.setPageNumber(pageProducts.getNumber());
		pr.setPageSize(pageProducts.getSize());
		pr.setTotalElements(pageProducts.getTotalElements());
		pr.setTotalPages(pageProducts.getTotalPages());
		pr.setIslastPage(pageProducts.isLast());
		return pr;
	}
	
	public String updateProduct(ProductRequest p,Integer productId) {
		Optional<Product> optProduct = productRepo.findById(productId);
		if(optProduct.isEmpty())
			throw new ProductException("Invalid product id");
		Product product = optProduct.get();
		product.setTitle(p.getTitle());
		product.setDescription(p.getDescription());
		product.setPrice(p.getPrice());
		Category category = categoryService.findCategoryByName(p.getCategory());
		if(category == null)
			throw new CategoryException("Catgory does not exist with this name");
		product.setCategory(category);
	    productRepo.saveAndFlush(product);
	    return "Product updated successfully";
	}
	
	public String deleteProduct(Integer productId) {
		Optional<Product> optProduct = productRepo.findById(productId);
		if(optProduct.isEmpty())
			throw new ProductException("Invalid product id");
		Product product = optProduct.get();
		 productRepo.delete(product);
		 return "Product deleted successfully";
	}
	
	
	public Product findProductById(Integer productId) {
		if(productId==null)
			throw new ProductException("Product id not received");
		Optional<Product> optProduct = productRepo.findById(productId);
		if(optProduct.isEmpty())
			throw new ProductException("Product does not exists with this id");
		return optProduct.get();
    }
}
