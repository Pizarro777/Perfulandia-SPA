package com.pizarro777.msvc.clientes.clients;

import com.pizarro777.msvc.clientes.dtos.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-carrito", url = "http://localhost:8001/api/carritos")
public interface CarritoClientRest {

    @GetMapping("/{id}")
    CarritoDTO obtenerCarrito(@PathVariable("id") Long id);
}


