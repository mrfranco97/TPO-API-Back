package com.uade.tpo.megagame.interfaces;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.entity.Tipo;
import com.uade.tpo.megagame.exception.ProductoDuplicadoException;

public interface ProductoInterface {
    public Page<Producto> getProductos(PageRequest pageRequest);
    public List<Producto> getProductoByNombre(String productoName);
    public Optional<Producto> getProductoById(Long productoId);
    public Producto createProducto(String nombre,String descripcion,Blob imagen,Double precio,Float descuento,LocalDate lanzamiento,String desarrolador,Long id_tipo,Integer stock, Boolean flag_destacar) throws ProductoDuplicadoException;
    public void eliminarProducto(Long id);
    public Producto modificarProducto(Producto modificacion);
    public List<Producto> getProductosDestacados();
    void modificarStock(Long idProducto, int cantidad);
    public List<Tipo> getTipo();
    public Optional<Tipo> getTipoByID(Long tipoID);
}
