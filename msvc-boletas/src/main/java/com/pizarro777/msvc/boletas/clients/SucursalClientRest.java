package com.pizarro777.msvc.boletas.clients;

import com.pizarro777.msvc.surcursal.dtos.SurcursalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-surcursal", url = "localhost:8080")
public interface SucursalClientRest {
    @GetMapping("/surcursal/{idSurcursal}")
    SurcursalDTO obtenerSurcursalPorId(@PathVariable("idSurcursal") Long idProductos);
}
