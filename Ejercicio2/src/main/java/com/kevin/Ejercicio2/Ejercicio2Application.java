package com.kevin.Ejercicio2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class Ejercicio2Application {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio2Application.class, args);
	}

}
