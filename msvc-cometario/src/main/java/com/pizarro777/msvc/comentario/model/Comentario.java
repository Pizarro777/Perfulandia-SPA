package com.pizarro777.msvc.comentario.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "comentarios")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Comentario {

    // Id del Comentario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long idComentario;

    //Añadir clienteid

    // Commentario
    @Column(nullable = false, length = 500)
    private String comentario;

    // Fecha de creacion del comentario
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, length = 100)
    private LocalDate fechaCreacion = LocalDate.now();

    // Id del Producto al que pertenece el comentario
    @Column(nullable = false)
    @NotNull(message = "El id del producto es obligatorio")
    private Long idProducto;

}
