package com.pizarro777.msvc.boletas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-clientes", url = "http://localhost:8002/api/clientes")
public interface ClientesClientRest {

    @GetMapping("/{id}")
    String getCliente(@PathVariable("id") String id);
}
