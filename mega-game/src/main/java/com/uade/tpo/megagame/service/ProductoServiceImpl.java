package com.uade.tpo.megagame.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.repository.ProductoRepository;

public class ProductoServiceImpl implements ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    public Page<Producto> getProductos(PageRequest pageRequest){
            return productoRepository.findAll(pageRequest);
    }

    public Optional<Producto> getProductoById(Long productoId){
        return productoRepository.findById(productoId);
    }
    //Evite los datos que dependen de las relaciones Genero,Tipo
    public Producto createProducto(String nombre){
        return null;
    }

    public void eliminarProducto(Long id){

    }

    public void modificarProducto(Producto modificacion){

    }

    public Page<Producto> getProductosDestacados(){
        return null;
    }

}
