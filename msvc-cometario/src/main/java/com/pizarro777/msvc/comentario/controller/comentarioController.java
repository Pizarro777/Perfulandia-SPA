package com.pizarro777.msvc.comentario.controller;


import com.pizarro777.msvc.comentario.model.comentario;
import com.pizarro777.msvc.comentario.service.comentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comentarios")
public class comentarioController {

    @Autowired
    private comentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<comentario>> findAll() {
        List<comentario> comentarios = comentarioService.findAll();
        if (comentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<comentario> findById(Long idComentario) {
        comentario comentario = comentarioService.findById(idComentario);
        if (comentario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comentario);
    }

    @PostMapping
    public ResponseEntity<comentario> save(comentario comentario) {
        if (comentario.getComentario() == null || comentario.getComentario().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        comentario savedComentario = comentarioService.save(comentario);
        return ResponseEntity.ok(savedComentario);
    }
}
