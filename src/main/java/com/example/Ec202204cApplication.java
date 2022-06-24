package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Ec202204cApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ec202204cApplication.class, args);
	}

}
