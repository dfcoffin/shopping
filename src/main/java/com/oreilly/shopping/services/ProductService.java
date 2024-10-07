package com.oreilly.shopping.services;

import com.oreilly.shopping.dao.ProductRepository;
import com.oreilly.shopping.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author Donald F. Coffin, Green Button Alliance, Inc.
 **/

@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void initializeDatabase() {
		if (productRepository.count() == 0) {
			productRepository.saveAll(
					List.of(
							new Product("TV tray", BigDecimal.valueOf(4.95)),
							new Product("Toaster", BigDecimal.valueOf(19.95)),
							new Product("Sofa", BigDecimal.valueOf(249.95)),
							new Product("Microwave", BigDecimal.valueOf(49.95))
					)
			).forEach(System.out::println);
		}
	}

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> findProductById(Long id) {
		return productRepository.findById(id);
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Product p) {
		productRepository.delete(p);
	}

	public void deleteAllProducts() {
		productRepository.deleteAllInBatch();
	}

	public List<Product> findAllProductsByMinPrice(double minPrice) {
		return productRepository.findAllByPriceGreaterThanEqual(BigDecimal.valueOf(minPrice));
	}
}
