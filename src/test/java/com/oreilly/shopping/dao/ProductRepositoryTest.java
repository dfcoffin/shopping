package com.oreilly.shopping.dao;

import com.oreilly.shopping.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Donald F. Coffin, Green Button Alliance, Inc.
 **/

@SpringBootTest
@Transactional
public class ProductRepositoryTest {
	@Autowired
	private ProductRepository repository;

	@Test
	void countProducts() {
		assertEquals(4, repository.count());
	}

	@Test
	void findById() {
		assertTrue(repository.findById(1L).isPresent());
	}

	@Test
	void findAll() {
		repository.findAll().forEach(System.out::println);
		assertEquals(4, repository.findAll().size());
	}

	@Test
	void insertProduct() {
		Product bat = new Product("Cricket Bat",  BigDecimal.valueOf(35.00));
		repository.save(bat);
		assertAll(
				() -> assertNotNull(bat.getId()),
				() -> assertEquals(5, repository.count())
		);
	}

	@Test
	void deleteProduct() {
		repository.deleteById(1L);
		assertEquals(3, repository.count());
	}

	@Test
	void deleteAllInBatch() {
		repository.deleteAllInBatch();
		assertEquals(0, repository.count());
	}

	@Test
	void deleteAllProducts() {
		repository.deleteAll();
		assertEquals(0, repository.count());
	}
}
