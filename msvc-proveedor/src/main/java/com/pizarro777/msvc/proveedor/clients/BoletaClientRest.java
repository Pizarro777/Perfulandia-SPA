package com.pizarro777.msvc.proveedor.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-boletas", url = "http://localhost:8000/api/boletas")
public interface BoletaClientRest {

    @GetMapping("/{id}")
    String obtenerBoletaPorId(@PathVariable("id") Long id);

}
