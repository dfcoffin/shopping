package com.oreilly.shopping.controllers;

import com.oreilly.shopping.entities.Product;
import com.oreilly.shopping.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Donald F. Coffin, Green Button Alliance, Inc.
 **/

@RestController
@RequestMapping("/products")
public class ProductRestController {

	private final ProductService service;

	public ProductRestController(ProductService service) {
		this.service = service;
	}

	@GetMapping
	public List<Product> getProducts() {
		return service.findAllProducts();
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		return ResponseEntity.of(service.findProductById(id));
	}

	@GetMapping(params = "min")
	public List<Product> getProductsByMinPrice(@RequestParam(defaultValue = "0.0") double min) {
			if (min < 0) throw new ProductMinimumPriceException(min);
			return service.findAllProductsByMinPrice(min);
	}


	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product p = service.saveProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(p.getId())
				.toUri();
		return ResponseEntity.created(location).body(p);
	}

	@PutMapping("{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return service.findProductById(id)
				.map(p -> {
					p.setName(product.getName());
					p.setPrice(product.getPrice());
					return ResponseEntity.ok(service.saveProduct(p));
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		return service.findProductById(id)
				.map(p -> {
					service.deleteProduct(p);
					return ResponseEntity.noContent().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping
	public ResponseEntity<Product> deleteAllProducts() {
		service.deleteAllProducts();
		return ResponseEntity.noContent().build();
	}
}
