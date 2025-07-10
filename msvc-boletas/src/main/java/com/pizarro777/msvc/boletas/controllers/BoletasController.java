package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.dtos.ErrorDTO;
import com.pizarro777.msvc.boletas.models.entities.Boletas;
import com.pizarro777.msvc.boletas.services.BoletasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/boletas")
@Validated
@Tag(name = "Boletas", description = "Esta seccion contiene los CRUD de boletas")
public class BoletasController {


    @Autowired
    private BoletasService boletasService;


    @PostMapping
    @Operation(
            summary = "Crear nueva boleta",
            description = "Crea una nueva boleta con la información enviada en el cuerpo de la petición."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Boleta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incorrectos")
    })

    public ResponseEntity<Boletas> crearBoletas(@RequestBody Boletas boleta) {
        try {
            Boletas nuevaBoleta = boletasService.save(boleta);

            log.info("API: Boleta creada exitosamente con ID: {} y número: {}", nuevaBoleta.getIdBoletas(), nuevaBoleta.getNombreBoletas());

            return new ResponseEntity<>(nuevaBoleta, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            log.warn("Error al crear boleta debido a argumentos inválidos: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Ocurrió un error inesperado al crear la boleta", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obtiene una boleta por ID", description = "A través del id suministrado devuelve la boleta con esa id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Operacion existosa"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Boleta no encontrada, con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema =  @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico de la boleta", required = true)
    })


    @GetMapping("/{id}")
    public ResponseEntity<Boletas> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.boletasService.findById(id));
    }

    @GetMapping
    @Operation(
            summary = "Devuelve todas las boletas",
            description = "Este metodo debe retornar un List de Boletas, en caso"+
                    "de que no encuentre nada retorna List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos las boletas OK")
    })

    public ResponseEntity<List<Boletas>> findAll() {
        List<Boletas> Boletas = boletasService.findAll();
        if (Boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Boletas);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar boleta",
            description = "Actualiza la boleta que coincida con el ID proporcionada con la información enviada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleta actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incorrectos"),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada para actualizar")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único de boleta a actualizar", required = true)
    })

    public ResponseEntity<Boletas> actualizarBoletas(@PathVariable Long id, @RequestBody Boletas boletas) {
        Boletas actualizado = boletasService.actualizarBoletas(id, boletas);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar boleta",
            description = "Elimina la boleta identificada por el ID proporcionada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Boleta eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada para eliminar")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único de boleta a eliminar", required = true)
    })

    public ResponseEntity<Void> eliminarBoletas(@PathVariable("id") Long id) {
        boletasService.eliminarBoletas(id);
        return ResponseEntity.noContent().build();
    }



}
