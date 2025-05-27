package com.pizarro777.msvc.productos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-proveedor", url = "http://localhost:8006/api/proveedor")
public interface ProveedorClientRest {

    @GetMapping("/{id}")
    String obtenerProveedor(@PathVariable("id") Long id);
}
