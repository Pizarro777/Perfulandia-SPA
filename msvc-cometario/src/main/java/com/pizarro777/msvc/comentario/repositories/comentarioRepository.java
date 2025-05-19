package com.pizarro777.msvc.comentario.repositories;


import com.pizarro777.msvc.comentario.model.comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface comentarioRepository  extends JpaRepository<comentario, Long> {

    List<comentario> findByidUsuario(Long idComentario);

}
