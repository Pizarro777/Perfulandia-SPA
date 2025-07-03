package com.pizarro777.msvc.proveedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@SpringBootApplication
public class MsvcProveedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProveedorApplication.class, args);
	}

}
