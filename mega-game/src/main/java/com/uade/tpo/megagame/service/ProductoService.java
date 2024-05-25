package com.uade.tpo.megagame.service;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.exception.ProductoDuplicadoException;

public interface ProductoService {
    public Page<Producto> getProductos(PageRequest pageRequest);

    public Optional<Producto> getProductoById(Long productoId);

    public Producto createProducto(String nombre) throws ProductoDuplicadoException;

    public void eliminarProducto(Long id);

    public void modificarProducto(Producto modificacion);

    public Page<Producto> getProductosDestacados();


}
