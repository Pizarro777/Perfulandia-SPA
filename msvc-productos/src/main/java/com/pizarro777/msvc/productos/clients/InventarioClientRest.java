package com.pizarro777.msvc.productos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-inventario", url = "http://localhost:8004/api/inventarios")
public interface InventarioClientRest {

    @GetMapping("/{id}")
    String obtenerInventario(@PathVariable("id") Long id);
}
