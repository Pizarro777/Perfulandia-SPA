    package com.pizarro777.msvc.clientes.repositories;

    import com.pizarro777.msvc.clientes.models.Cliente;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    }
