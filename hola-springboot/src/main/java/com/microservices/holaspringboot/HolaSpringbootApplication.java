package com.microservices.holaspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.microservices.holaspringboot.controller.GreeterRestController;

@SpringBootApplication
//@ComponentScan(basePackageClasses = GreeterRestController.class)
public class HolaSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolaSpringbootApplication.class, args);
	}
}
