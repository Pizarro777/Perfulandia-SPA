package com.msvc.detalleBoleta.msvc_detalleBoleta.clients;


import com.pizarro777.msvc.productos.dtos.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-productos", url = "localhost:8080")
public interface ProductoClientRest {

    @GetMapping("/productos/{idProducto}")
    ProductoDTO obtnerProductoPorId(@PathVariable("idProducto") Long idProducto);
}
