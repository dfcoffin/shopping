package com.oreilly.shopping.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.ProblemDetail.*;

/**
 * @author Donald F. Coffin, Green Button Alliance, Inc.
 **/

@RestControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductMinimumPriceException.class)
	public ProblemDetail handleProductMinimumPriceException(ProductMinimumPriceException ex) {
		return forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
}
