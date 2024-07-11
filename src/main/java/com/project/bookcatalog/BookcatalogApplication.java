package com.project.bookcatalog;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;



@SpringBootApplication
@OpenAPIDefinition(info=@Info(description = "bookscatalogsystem", title = "my api",version = "2.0.2"))
public class BookcatalogApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(BookcatalogApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		System.out.println("Success");
		
	}

}