package com.msvc.detalleBoleta.msvc_detalleBoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
public class MsvcDetalleBoletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcDetalleBoletaApplication.class, args);
	}

}
