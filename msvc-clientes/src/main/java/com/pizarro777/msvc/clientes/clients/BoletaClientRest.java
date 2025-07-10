package com.pizarro777.msvc.clientes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-boletas", url= "http://localhost:8000/api/v1/boletas")
public interface BoletaClientRest {

    @GetMapping("/{id}")
    String obtenerBoleta(@PathVariable("id") Long idComentario);

}

