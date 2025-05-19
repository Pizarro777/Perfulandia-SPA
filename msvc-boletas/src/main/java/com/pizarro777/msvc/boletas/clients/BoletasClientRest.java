package com.pizarro777.msvc.boletas.clients;

import com.pizarro777.msvc.boletas.models.Boletas;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "msvc-boletas", url = "localhost:8080/api/v1/boletas")
public interface BoletasClientRest {

    @GetMapping("/{id}")
    Boletas obtenerBoletaPorId(@PathVariable Long id);

}
