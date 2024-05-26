package com.project.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryRequest {
	
	@NotEmpty(message = "Category name cannot be empty")
	@Size(min = 1,max = 25,message = "Category name must contain minimum 2 characters and maximum 25 characters")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
