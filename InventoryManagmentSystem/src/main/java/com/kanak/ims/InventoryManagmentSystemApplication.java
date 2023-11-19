package com.kanak.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class InventoryManagmentSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryManagmentSystemApplication.class, args);
	}

}
