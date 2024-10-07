package com.oreilly.shopping;

import com.oreilly.shopping.configs.ContainersConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

/**
 * @author Donald F. Coffin, Green Button Alliance, Inc.
 **/

public class ShoppingTestApplication {

	public static void main(String[] args) {
		SpringApplication
				.from(ShoppingTestApplication::main)
				.with(ContainersConfig.class)
				.run(args);
	}
}
