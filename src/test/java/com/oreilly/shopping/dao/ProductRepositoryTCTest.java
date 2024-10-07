package com.oreilly.shopping.dao;

import com.oreilly.shopping.configs.ContainersConfig;
import com.oreilly.shopping.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Donald F. Coffin, Green Button Alliance, Inc.
 **/

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ContainersConfig.class)
public class ProductRepositoryTCTest {
//	@Container
//	@ServiceConnection
//	private static final PostgreSQLContainer<?> postgreSQLContainer =
//			new PostgreSQLContainer<>("postgres:15-alpine");

	@Autowired
	private PostgreSQLContainer<?> postgreSQLContainer;

	@Autowired
	private ProductRepository repository;

	@Test
	void testRepository() {
		System.out.println("Test container running on port: " + postgreSQLContainer.getFirstMappedPort());
		System.out.println("Test container running on host:" + postgreSQLContainer.getHost());
		System.out.println("Test container running on JDBC URL: " + postgreSQLContainer.getJdbcUrl());
		System.out.println("Test container running on username: " + postgreSQLContainer.getUsername());
		System.out.println("Test container running on password: " + postgreSQLContainer.getPassword());
		System.out.println("Test container running on database name: " + postgreSQLContainer.getDatabaseName());
		System.out.println("Test container running on driver class name: " + postgreSQLContainer.getDriverClassName());
	}

	@Test
	void testSave() {
		Product product = new Product("Samsung TV", BigDecimal.valueOf(500.00));
		repository.saveAndFlush(product);

		assertThat(repository.count()).isEqualTo(1L);
	}
}
