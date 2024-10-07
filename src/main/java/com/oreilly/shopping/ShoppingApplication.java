package com.oreilly.shopping;

import com.oreilly.shopping.dao.ProductRepository;
import com.oreilly.shopping.entities.Product;
import com.oreilly.shopping.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeDatabase(ProductService service) {
		return args -> service.initializeDatabase();
	}

}
