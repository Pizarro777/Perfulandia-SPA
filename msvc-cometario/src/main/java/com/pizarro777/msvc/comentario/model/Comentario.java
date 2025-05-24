package com.pizarro777.msvc.comentario.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha_creacion")
    private Long idPublicacion;

    @Column(name = "id_producto")
    private Long idProducto;


    private LocalDateTime fecha = LocalDateTime.now();

}
