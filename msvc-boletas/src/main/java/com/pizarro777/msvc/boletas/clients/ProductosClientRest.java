package com.pizarro777.msvc.boletas.clients;

import com.pizarro777.msvc.productos.dtos.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-productos", url = "localhost:8080")
public interface ProductosClientRest {

    @GetMapping("/productos/{idProducto}")
    ProductoDTO obtenerProductoPorId(@PathVariable("idProducto") Long idProducto);

}
