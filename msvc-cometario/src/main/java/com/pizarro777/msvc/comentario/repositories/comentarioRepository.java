package com.pizarro777.msvc.comentario.repositories;


import com.pizarro777.msvc.comentario.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface comentarioRepository  extends JpaRepository<Comentario, Long> {

    List<Comentario> findByidUsuario(Long idComentario);

}
