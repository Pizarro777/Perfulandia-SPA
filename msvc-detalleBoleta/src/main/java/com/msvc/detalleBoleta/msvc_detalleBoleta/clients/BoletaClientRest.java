package com.msvc.detalleBoleta.msvc_detalleBoleta.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "msvc-boletas", url = "localhost:8080")
public interface BoletaClientRest {

    @GetMapping("/boletas/{idBoletas}")


}
