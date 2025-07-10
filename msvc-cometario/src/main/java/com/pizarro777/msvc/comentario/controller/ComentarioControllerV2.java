package com.pizarro777.msvc.comentario.controllers;



import com.pizarro777.msvc.comentario.assamblers.ComentarioModelAssembler;
import com.pizarro777.msvc.comentario.dtos.ErrorDTO;
import com.pizarro777.msvc.comentario.model.Comentario;
import com.pizarro777.msvc.comentario.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/comentarios")
@Tag(name = "Comentarios V2", description = "Operaciones CRUD sobre comentarios con HATEOAS")
public class ComentarioControllerV2 {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private ComentarioModelAssembler comentarioModelAssembler;

    @GetMapping
    @Operation(summary = "Obtiene todos los comentarios", description = "Devuelve una lista de comentarios")
    @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Comentario.class))
    )
    public ResponseEntity<CollectionModel<EntityModel<Comentario>>> findAll() {
        List<EntityModel<Comentario>> comentarios = comentarioService.findAll()
                .stream()
                .map(comentarioModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Comentario>> collection = CollectionModel.of(
                comentarios,
                linkTo(methodOn(ComentarioControllerV2.class).findAll()).withSelfRel()
        );

        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un comentario por ID", description = "Devuelve un comentario específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentario encontrado",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Comentario.class))),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del comentario", required = true)
    })
    public ResponseEntity<EntityModel<Comentario>> findById(@PathVariable Long id) {
        Comentario comentario = comentarioService.findById(id);
        EntityModel<Comentario> entityModel = comentarioModelAssembler.toModel(comentario);
        return ResponseEntity.ok(entityModel);
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo comentario", description = "Permite registrar un nuevo comentario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comentario creado exitosamente",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Comentario.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Comentario a crear",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comentario.class))
    )
    public ResponseEntity<EntityModel<Comentario>> create(@Valid @RequestBody Comentario comentario) {
        Comentario nuevo = comentarioService.save(comentario);
        EntityModel<Comentario> entityModel = comentarioModelAssembler.toModel(nuevo);

        return ResponseEntity
                .created(linkTo(methodOn(ComentarioControllerV2.class).findById(nuevo.getIdComentario())).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un comentario por ID", description = "Permite eliminar un comentario específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comentario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del comentario a eliminar", required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        comentarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

