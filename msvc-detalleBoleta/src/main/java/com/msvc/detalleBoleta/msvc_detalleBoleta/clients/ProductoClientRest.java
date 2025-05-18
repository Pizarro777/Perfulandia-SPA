package com.msvc.detalleBoleta.msvc_detalleBoleta.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "msvc-productos", url = "localhost:8080")
public interface ProductoClientRest {

}
