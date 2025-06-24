package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.clients.ProductoClientRest;
import com.pizarro777.msvc.boletas.models.BoletasModel;
import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BoletaServicelmpl implements BoletasService{

    @Autowired
    private RepositoryBoletas repositoryBoletas;

    @Autowired
    private ProductoClientRest productoClientRest;


    @Override
    public List<Boletas> findAll() {
        return repositoryBoletas.findAll();
    }


    @Override
    public Boletas findById(Long id) {
        return repositoryBoletas.findById(id).orElse(null);
    }

    @Override
    public Boletas save(Boletas boletas) {
        for (ItemBoletas item : boletas.getItems()) {
            ProductoDTO producto = productoClientRest.obtenerProducto(item.getIdProducto());

            item.setNombre( producto.getNombre());

        }

        return repositoryBoletas.save(boletas);
    }

}
