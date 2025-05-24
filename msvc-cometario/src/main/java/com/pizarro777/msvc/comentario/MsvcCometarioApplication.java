package com.pizarro777.msvc.comentario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.pizarro777.msvc.comentario.clients")
public class MsvcCometarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCometarioApplication.class, args);
	}

}
