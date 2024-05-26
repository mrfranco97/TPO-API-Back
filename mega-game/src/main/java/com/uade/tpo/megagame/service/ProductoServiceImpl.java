package com.uade.tpo.megagame.service;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.exception.ProductoDuplicadoException;
import com.uade.tpo.megagame.repository.ProductoRepository;

@Service
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
    public Producto createProducto(String nombre) throws ProductoDuplicadoException{
        List<Producto> productos = productoRepository.findByName(nombre);
        if (productos.isEmpty())
            return productoRepository.save(new Producto(nombre));
        throw new ProductoDuplicadoException();
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }

    public Producto modificarProducto(Producto modificacion){
        return productoRepository.save(modificacion);
    }

    public Page<Producto> getProductosDestacados(){
        return null;
    }

}
