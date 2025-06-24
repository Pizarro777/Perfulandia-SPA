package com.pizarro777.msvc.proveedor.services;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProveedorServicelmpl implements ProveedorService{

    @Autowired
    private RepositoryProveedor repositoryProveedor;

    @Autowired
    private ProductoClientRest productoClientRest;


    @Override
    public List<Proveedor> findAll() {
        return repositoryProveedor.findAll();
    }


    @Override
    public Proveedor findById(Long id) {
        return repositoryProveedor.findById(id).orElse(null);
    }


    @Override
    public Proveedor save(Proveedor proveedor) {
        for (ItemCProveedor item : proveedir.getItems()) {
            ProductoDTO producto = productoClientRest.obtenerProducto(item.getIdProducto());

            item.setNombre( producto.getNombre());

        }

        return repositoryProveedor.save(proveedor);
    }

    @Override
    public void delete(Long id) {
        repositoryProveedor.deleteById(id);
    }
}
