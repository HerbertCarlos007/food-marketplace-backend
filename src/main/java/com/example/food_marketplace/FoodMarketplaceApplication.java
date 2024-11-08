package com.example.food_marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.food_marketplace.repositories")
public class FoodMarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodMarketplaceApplication.class, args);
	}

}
