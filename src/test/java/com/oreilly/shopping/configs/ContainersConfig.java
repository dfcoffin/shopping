package com.oreilly.shopping.configs;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 *
 *  @author Donald F. Coffin, Green Button Alliance, Inc.
 *
 **/

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfig {
	@Bean
	@ServiceConnection
	@RestartScope
	public PostgreSQLContainer<?> postgreSQLContainer() {
	return new PostgreSQLContainer<>("postgres:15.2-alpine");
	}
}
