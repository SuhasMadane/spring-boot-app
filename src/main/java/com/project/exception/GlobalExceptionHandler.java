package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity handleProductException(ProductException p) {
		ApiResponse a = new ApiResponse();
		a.setMessage(p.getMessage());
		a.setStatus(true);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(a);
	}
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity handleCategoryException(CategoryException c) {
		ApiResponse a = new ApiResponse();
		a.setMessage(c.getMessage());
		a.setStatus(true);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(a);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		ApiResponse a = new ApiResponse();
		a.setMessage(e.getMessage());
		a.setStatus(false);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(a);
	}

}
