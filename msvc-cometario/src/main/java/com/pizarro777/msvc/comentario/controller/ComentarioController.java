package com.pizarro777.msvc.comentario.controller;


import com.pizarro777.msvc.comentario.model.Comentario;
import com.pizarro777.msvc.comentario.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    /* Obtiene la lista de todos los comentarios. */
    @GetMapping
    public ResponseEntity<List<Comentario>> findAll() {
        List<Comentario> Comentarios = comentarioService.findAll();
        if (Comentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Comentarios);
    }
    /* Busca un comentario por ID. */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Comentario> findById(@PathVariable Long idComentario) {
        Comentario comentario = comentarioService.findById(idComentario);
        if (comentario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comentario);
    }
    /* Guarda nuevo comentario. */
    @PostMapping
    public ResponseEntity<Comentario> save(@RequestBody @Valid Comentario comentario) {
        if (comentario.getComentario() == null || comentario.getComentario().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Comentario savedComentario = comentarioService.save(comentario);
        return ResponseEntity.ok(savedComentario);
    }

    /* Actualiza un comentario por ID. */
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        Comentario actualizado = comentarioService.update(id, comentario);
        return ResponseEntity.ok(actualizado);
    }

    /* Elimina un comentario por ID. */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long idComentario) {
        comentarioService.delete(idComentario);
        return ResponseEntity.noContent().build();
    }
}
