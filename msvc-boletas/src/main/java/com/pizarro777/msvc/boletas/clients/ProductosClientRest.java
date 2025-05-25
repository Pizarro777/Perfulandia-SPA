package com.pizarro777.msvc.boletas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-productos", url = "localhost:8006")
public interface ProductosClientRest {

    @GetMapping("/productos/{id}")
    String obtenerProductoPorId(@PathVariable("id") String id);


}
