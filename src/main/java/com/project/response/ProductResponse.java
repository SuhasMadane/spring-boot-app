package com.project.response;

import java.util.List;

import com.project.model.Product;

public class ProductResponse {

	private List<Product> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean islastPage;
	
	
	public ProductResponse(List<Product> content, int pageNumber, int pageSize, long totalElements, int totalPages,
			boolean islastPage) {
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.islastPage = islastPage;
	}


	public ProductResponse() {
	}


	public List<Product> getContent() {
		return content;
	}


	public void setContent(List<Product> content) {
		this.content = content;
	}


	public int getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public long getTotalElements() {
		return totalElements;
	}


	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public boolean isIslastPage() {
		return islastPage;
	}


	public void setIslastPage(boolean islastPage) {
		this.islastPage = islastPage;
	}
	
}
