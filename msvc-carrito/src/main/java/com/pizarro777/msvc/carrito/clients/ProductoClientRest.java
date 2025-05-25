package com.pizarro777.msvc.carrito.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-productos", url = "localhost:8002")
public interface ProductoClientRest {

    @GetMapping("/productos/{id}")
    String obtenerProductoPorId(@PathVariable("id") Long id);
}
