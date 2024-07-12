package com.uade.tpo.megagame.service;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

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
    public List<Producto> getProductoByNombre(String productoName){
        return productoRepository.findLikeName(productoName);
    }
    public Optional<Producto> getProductoById(Long productoId){
        return productoRepository.findById(productoId);
    }
    //Evite los datos que dependen de las relaciones Genero,Tipo
    public Producto createProducto(String nombre,String descripcion,Blob imagen,Double precio,Float descuento,LocalDate lanzamiento,String desarrolador,Long id_tipo,Integer stock, Boolean flag_destacar) throws ProductoDuplicadoException{
        List<Producto> productos = productoRepository.findByName(nombre);
        Optional<Tipo> consulta = tipoRepository.findById(id_tipo);
        if (productos.isEmpty() && consulta.isPresent()){
            Tipo tipo = consulta.get();
            return productoRepository.save(new Producto(nombre,descripcion,imagen,precio,descuento,lanzamiento,desarrolador,tipo,stock, flag_destacar));
        }
        throw new ProductoDuplicadoException();
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }

    public Producto modificarProducto(Producto modificacion){
        return productoRepository.save(modificacion);
    }

    public List<Producto> getProductosDestacados(){
        return productoRepository.findDestacados();
    }


    public void modificarStock(Long idProducto, int cantidad) {
        productoRepository.modificarStock(idProducto, cantidad);
    }

    public List<Tipo> getTipo() {
        return tipoRepository.findAll();
    }

    public Optional<Tipo> getTipoByID(Long tipoID) {
        return tipoRepository.findById(tipoID);
    }
}
