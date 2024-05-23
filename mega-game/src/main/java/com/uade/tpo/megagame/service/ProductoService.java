package com.uade.tpo.megagame.service;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.uade.tpo.megagame.entity.Producto;

public interface ProductoService {
    public Page<Producto> getProductos(PageRequest pageRequest);

    public Optional<Producto> getProductoById(Long productoId);
    //Evite los datos que dependen de las relaciones Genero,Tipo
    public Producto createProducto(String nombre);
     //throws CategoryDuplicateException;
    public void eliminarProducto(Long id);

    public void modificarProducto(Producto modificacion);

    public Page<Producto> getProductosDestacados();


}
