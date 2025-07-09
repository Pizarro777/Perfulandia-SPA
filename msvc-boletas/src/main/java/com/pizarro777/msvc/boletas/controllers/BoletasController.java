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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/boletas")
@Validated
@Tag(name = "Boletas", description = "Esta seccion contiene los CRUD de boletas")
public class BoletasController {

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



    @GetMapping("/{id}")
    public ResponseEntity<Boletas> findById(@PathVariable("id") Long id) { // El path variable en la URL es 'id'
        Boletas boletas = boletasService.findById(id); // Pasar 'id' al servicio

        if (boletas == null) { // Si el servicio devuelve null
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found
        }
        return ResponseEntity.ok(boletas);
    }



    @Operation(
            summary = "Guarda una boleta",
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
            description = "boleta a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Boletas.class)
            )
    )


    @PostMapping // Mapea las solicitudes HTTP POST a la ruta base /api/boletas
    public ResponseEntity<Boletas> crearBoletas(@RequestBody Boletas boleta) {
        try {

            Boletas nuevaBoleta = boletasService.save(boleta);

            System.out.println("API: Boleta creada exitosamente con ID: " + nuevaBoleta.getIdBoletas() + " y número: " + nuevaBoleta.getNombreBoletas());

            return new ResponseEntity<>(nuevaBoleta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBoletas(@PathVariable("id") Long id) {
        boletasService.eliminarBoletas(id);
        return ResponseEntity.noContent().build();
    }

}
