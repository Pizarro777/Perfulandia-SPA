package com.pizarro777.msvc.proveedor.clients;

import com.pizarro777.msvc.boletas.dtos.BoletasDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-boletas", url = "localhost:8080")
public interface BoletaClientRest {
    @GetMapping("/boletas/{idBoletas}")
    BoletasDTO obtenerBoletaPorId(@PathVariable("idBoletas") Long idBoletas);

}
