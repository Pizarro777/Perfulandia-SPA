package com.pizarro777.msvc.carrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.pizarro777.msvc.carrito.clients")
@SpringBootApplication
public class MsvcCarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCarritoApplication.class, args);
	}

}
