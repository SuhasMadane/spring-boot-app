package com.project.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequest {

	@NotEmpty(message = "Product title cannot be empty")
	@Size(min = 2,max = 25,message = "Product title must contain minimum 2 characters and maximum 25 characters")
	private String title;
	
	@NotEmpty(message = "Product description cannot be empty")
	private String description;
	
	@NotNull
	@Min(value = 1,message = "Product price must be greater than 0")
	private Float price;
	
	@NotEmpty(message = "Product category cannot be empty")
	private String category;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
