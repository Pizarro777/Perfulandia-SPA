package com.pizarro777.msvc.clientes.repositories;

import com.pizarro777.msvc.clientes.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /* Encuentra clientes por apellidos */
    @Query("SELECT c FROM cliente c WHERE c.apellidos= : apellido")
    List<Cliente> findbyApellido(@Param("apellidos") String apellido);


    /* Encuentra clientes por correo */
    @Query("SELECT c FROM cliente c WHERE c.correos= :correo")
    Cliente findByCorreo(String correo);


    /* Encuentra clientes por nombre y apellidos */
    @Query("SELECT c FROM cliente c WHERE c.nombres= : nombre")
    @Query("SELECT c FROM cliente c WHERE c.apellidos= : apellido")
    List<Cliente> findByNombreandApellido(String nombre, String apellido);



}
