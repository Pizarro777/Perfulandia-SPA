package com.pizarro777.msvc.inventario.clients;

import com.pizarro777.msvc.inventario.dtos.SucursalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-sucursales", url = "http://localhost:8007/api/v2/sucursales")
public interface SucursalClientFeign {
    @GetMapping("/{id}")
    SucursalDto obtenerSucursal(@PathVariable("id") Long id);
}