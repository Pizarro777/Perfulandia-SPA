package com.pizarro777.msvc.boletas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-productos", url = "http://localhost:8005/api/v1/productos")
public interface ProductoClientRest {
    @GetMapping("/{id}")
    String obtenerProductoPorId(@PathVariable("id") Long id);
}
