package com.pizarro777.msvc.clientes.controllers;


import com.pizarro777.msvc.clientes.models.Cliente;
import com.pizarro777.msvc.clientes.services.ClienteService;
import feign.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Validated
@Tag(name="Clientes", description = "Esta sección contiene los CRUD de Clientes.")
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /* Crear un nuevo Cliente */
    @PostMapping()
    @Operation(
            summary = "Crea un nuevo Cliente.",
            description = "Este metodo debe crear un cliente nuevo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El cliente ha sido creado."),
            @ApiResponse(responseCode = "400", description = "Error - Cliente no se logro crear.")
    })
    public ResponseEntity<Cliente> crearCliente(@RequestBody @Validated Cliente cliente){
        Cliente cli = clienteService.crearCliente(cliente);
        return ResponseEntity.status(201).body(cli);
    }

    /* Obtener un producto por ID */
    @GetMapping("/{id}")
    @Operation(
            summary = "Devuelve un Cliente por id.",
            description = "Este metodo debe retornar un Cliente, en caso "+
                    "de que no encuentre nada retorna."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorno el cliente ok."),
            @ApiResponse(responseCode = "400", description = "Error - Cliente con ID no existente.")
    })
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id){
        Cliente cli = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(cli);
    }

    /* Obtener todos los clientes */
    @GetMapping()
    @Operation(
            summary = "Devuelve todos los Cliente",
            description = "Este metodo debe retornar un List de Cliente, en caso "+
                    "de que no encuentre nada retorna."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los clientes ok"),
            @ApiResponse(responseCode = "400", description = "Error - Cliente con ID no existente")
    })
    public ResponseEntity<List<Cliente>> obtenerTodos(){
        return ResponseEntity.status(201).body(clienteService.listarTodos());

    }

    /* Actualizar Cliente  */
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualiza un Cliente por id.",
            description = "Este metodo debe actualizar un Cliente por id, en caso "+
                    "de que no encuentre nada retorna."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se Actualizo el cliente ok."),
            @ApiResponse(responseCode = "400", description = "Error - Cliente con ID no existente.")
    })
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody @Validated Cliente datos){
        Cliente cli = clienteService.actualizarCliente(id, datos);
        return ResponseEntity.ok(cli);
    }


    /* Eliminar un Cliente por ID */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Elimina un Cliente por id.",
            description = "Este metodo debe eliminar un Cliente, en caso "+
                    "de que no encuentre no eliminara nada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimino el cliente ok."),
            @ApiResponse(responseCode = "400", description = "Error - Cliente con ID no existente.")
    })
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
