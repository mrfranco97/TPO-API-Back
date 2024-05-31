package com.uade.tpo.megagame.service;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.entity.Tipo;
import com.uade.tpo.megagame.exception.ProductoDuplicadoException;
import com.uade.tpo.megagame.interfaces.ProductoInterface;
import com.uade.tpo.megagame.repository.ProductoRepository;
import com.uade.tpo.megagame.repository.TipoRepository;

@Service
public class ProductoService implements ProductoInterface {
    
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TipoRepository tipoRepository;

    public Page<Producto> getProductos(PageRequest pageRequest){
            return productoRepository.findAll(pageRequest);
    }

    public Optional<Producto> getProductoById(Long productoId){
        return productoRepository.findById(productoId);
    }


    //* inicio filtro */
    public Optional<Producto> getProductoByTipo(Long tipo){
        return productoRepository.findByTipo(tipo);
    }
    //* fin filtro */





    //Evite los datos que dependen de las relaciones Genero,Tipo
    public Producto createProducto(String nombre,String descripcion,String imagen,Double precio,LocalDate lanzamiento,String desarrolador,Long id_tipo,Integer stock) throws ProductoDuplicadoException{
        List<Producto> productos = productoRepository.findByName(nombre);
        Optional<Tipo> consulta = tipoRepository.findById(id_tipo);
        if (productos.isEmpty() && consulta.isPresent()){
            Tipo tipo = consulta.get();
            return productoRepository.save(new Producto(nombre,descripcion,imagen,precio,lanzamiento,desarrolador,tipo,stock));
        }
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
