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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/boletas")
@Validated
@Tag(name = "Boletas", description = "Esta seccion contiene los CRUD de boletas")
public class BoletaController {

    @Autowired
    private BoletasService boletasService;


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

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una boleta", description = "A través del id suministrado devuelve la boleta con esa id")
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



    @GetMapping(value = "/{id}")
    public ResponseEntity<Boletas> findById(@PathVariable Long idBoletas) {
        Boletas boletas = boletasService.findById(idBoletas);
        if (boletas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boletas);
    }



    @PostMapping
    @Operation(
            summary = "Guarda un medico",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Guardado exitoso"),
            @ApiResponse(
                    responseCode = "409",
                    description = "El medico guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "medico a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Medico.class)
            )
    )

    @PostMapping
    public ResponseEntity<Boletas> create(@RequestBody @Valid Boletas boletas) {
        Boletas nuevaBoletas = boletasService.save(boletas);
        return ResponseEntity.status(201).body(nuevaBoletas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBoletas(@PathVariable("id") Long id) {
        boletasService.eliminarBoletas(id);
        return ResponseEntity.noContent().build();
    }

}
