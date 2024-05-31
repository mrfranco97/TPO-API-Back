package com.uade.tpo.megagame.interfaces;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.exception.ProductoDuplicadoException;

public interface ProductoInterface {
    public Page<Producto> getProductos(PageRequest pageRequest);

    public Optional<Producto> getProductoById(Long productoId);

    //* inicio filtro */
    public Optional<Producto> getProductoByTipo(Long tipo);
    //* fin filtro */

    public Producto createProducto(String nombre,String descripcion,String imagen,Double precio,LocalDate lanzamiento,String desarrolador,Long id_tipo,Integer stock) throws ProductoDuplicadoException;

    public void eliminarProducto(Long id);

    public Producto modificarProducto(Producto modificacion);

    public Page<Producto> getProductosDestacados();


}
