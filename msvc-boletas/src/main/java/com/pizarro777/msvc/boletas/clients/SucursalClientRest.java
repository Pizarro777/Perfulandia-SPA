package com.pizarro777.msvc.boletas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-surcursal", url = "localhost:8008")
public interface SucursalClientRest {

    @GetMapping("/surcursal/{id}")
    String obtenerSucursalPorId(@PathVariable("idSurcursal") Long idSurcursal);
}
