package com.pizarro777.msvc.comentario.service;


import com.pizarro777.msvc.comentario.model.comentario;
import com.pizarro777.msvc.comentario.repositories.comentarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class comentarioService {

    @Autowired
    private comentarioRepository comentarioRepository;

    public List<comentario> findAll() {
        return comentarioRepository.findAll();
    }

    public comentario findById(Long idComentario) {
        return comentarioRepository.findById(idComentario).get();
    }

    public comentario save(comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void delete(Long idComentario) {
        comentarioRepository.deleteById(idComentario);
    }
}
