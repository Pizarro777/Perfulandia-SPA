package com.pizarro777.msvc.boletas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-carrito", url = "http://localhost:8002/api/carrito")
public interface CarritoClientRest {

    @GetMapping("/{id}")
    String getCarrito(@PathVariable("id") String id);
}
