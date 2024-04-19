package com.zigtong.clientserver.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@TestConfiguration
public class TestDatasourceConfiguration {
	@Primary
	@Bean
	public DataSource dataSource() {
		HikariDataSource hikariDataSource = (HikariDataSource)DataSourceBuilder.create()
			.driverClassName("org.h2.Driver")
			.url("jdbc:h2:mem:testdb;MODE=MySQL")
			.username("SA")
			.password("")
			.build();

		return hikariDataSource;
	}
}