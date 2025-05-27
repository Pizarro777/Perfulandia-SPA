package com.pizarro777.msvc.clientes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-comentario", url = "http://localhost:8003/api/comentarios")
public interface ComentariosClientRest {

    @GetMapping("/comentarios?usuarioId={id}")
    String obtenerComentario(@PathVariable("id") Long id);
}
