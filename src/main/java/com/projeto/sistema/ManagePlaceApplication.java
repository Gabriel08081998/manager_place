package com.projeto.sistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.projeto.sistema")
@EnableJpaRepositories(basePackages = "com.projeto.sistema.repository")
@EntityScan(basePackages = "com.projeto.sistema.model")
public class ManagePlaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagePlaceApplication.class, args);
	}

}
