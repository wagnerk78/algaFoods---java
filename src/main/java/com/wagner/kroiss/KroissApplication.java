package com.wagner.kroiss;

import com.wagner.kroiss.infrastructure.Specifications.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class KroissApplication {

	public static void main(String[] args) {
		SpringApplication.run(KroissApplication.class, args);
	}

}
