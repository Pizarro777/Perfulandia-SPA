package com.pizarro777.msvc.clientes.controllers;


import com.pizarro777.msvc.clientes.models.Cliente;
import com.pizarro777.msvc.clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Validated
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /* Crear un nuevo Cliente */
    @GetMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody @Validated Cliente cliente){
        Cliente cli = clienteService.crearCliente(cliente);
        return ResponseEntity.status(201).body(cli);
    }

    /* Obtener un producto por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id){
        Cliente cli = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(cli);
    }

    /* Obtener todos los clientes */
    @GetMapping("/todos")
    public List<Cliente> obtenerTodos(){
        return clienteService.listarTodos();
    }

    /* Actualizar Cliente  */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody @Validated Cliente datos){
        Cliente cli = clienteService.actualizarCliente(id, datos);
        return ResponseEntity.ok(cli);
    }


    /* Eliminar un Cliente por ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
