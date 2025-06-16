package com.pizarro777.msvc.inventario.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-productos", url = "http://localhost:8005/api/productos")
public interface ProductoClientFeign {
    @GetMapping("/{id}")
    String obtenerProducto(@PathVariable("id") Long id);
}
