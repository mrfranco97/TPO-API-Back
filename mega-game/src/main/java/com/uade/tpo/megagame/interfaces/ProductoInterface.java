package com.uade.tpo.megagame.interfaces;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.exception.ProductoDuplicadoException;

public interface ProductoInterface {
    public Page<Producto> getProductos(PageRequest pageRequest);

    public Optional<Producto> getProductoById(Long productoId);

    public Producto createProducto(String nombre) throws ProductoDuplicadoException;

    public void eliminarProducto(Long id);

    public Producto modificarProducto(Producto modificacion);

    public Page<Producto> getProductosDestacados();


}
