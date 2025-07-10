package com.pizarro777.msvc.clientes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-comentario", url = "http://localhost:8003/api/comentarios")
public interface ComentariosClientRest {

    @GetMapping
    String obtenerComentario(@RequestParam("usuarioId") Long id);
}

