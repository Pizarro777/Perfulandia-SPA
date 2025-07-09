package com.pizarro777.msvc.inventario.clients;

import com.pizarro777.msvc.inventario.dtos.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-productos", url = "http://localhost:8005/api/v2/productos")
public interface ProductoClientFeign {
    @GetMapping("/{id}")
    ProductoDto obtenerProducto(@PathVariable("id") Long id);
}
