package com.cg.bookstore.exception;

public class CategoryNotFoundException extends RuntimeException{

	public CategoryNotFoundException(String message) {
		super(message);
	}
}