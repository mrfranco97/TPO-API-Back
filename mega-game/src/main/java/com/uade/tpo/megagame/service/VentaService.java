package com.uade.tpo.megagame.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.entity.Usuario;
import com.uade.tpo.megagame.entity.Venta;
import com.uade.tpo.megagame.entity.VentaDetalle;
import com.uade.tpo.megagame.entity.dto.VentaDTO;
import com.uade.tpo.megagame.entity.dto.VentaDetalleDTO;
import com.uade.tpo.megagame.interfaces.VentaInterface;
import com.uade.tpo.megagame.repository.UsuarioRepository;
import com.uade.tpo.megagame.repository.VentaRepository;

@Service
public class VentaService implements VentaInterface {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoService productoService;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> findByIdUsuario(Long usuario) {
        return ventaRepository.findByIdUsuario(usuario);
    }

    public Venta save(VentaDTO ventaDTO) throws Exception {
        Venta venta = null;
        boolean ventaOk = true;

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(ventaDTO.getId_usuario());
        if (usuarioOptional.isPresent()) {
            venta = new Venta();
            venta.setUsuario(usuarioOptional.get());
            double subTotal = 0;
            double total = 0;
            for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {
                Optional<Producto> productoOptional = productoService.getProductoById(detalleDTO.getId_producto());
                if (productoOptional.isPresent()) {
                    Producto producto = productoOptional.get();
                    if (producto.getStock() >= detalleDTO.getCantidad()) {
                        VentaDetalle detalle = new VentaDetalle(detalleDTO.getCantidad());
                        detalle.setDescuento(producto.getDescuento());
                        detalle.setProducto(producto);
                        detalle.setVenta(venta);
                        venta.getDetalle().add(detalle);

                        // Calcular subtotal y total para cada producto y multiplicar por la cantidad
                        double precioTotalProducto = producto.getPrecio() * detalleDTO.getCantidad();
                        double precioTotalProductoDescuento = producto.getPrecioDescuento() * detalleDTO.getCantidad();

                        subTotal += precioTotalProducto;
                        total += precioTotalProductoDescuento;
                        
                    } else {
                        throw new Exception("Stock insuficiente para el producto: " + producto.getNombre());
                    }
                } else {
                    throw new Exception("Producto no encontrado: " + detalleDTO.getId_producto());
                }
            }

            if (ventaOk) {
                for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {
                    productoService.modificarStock(detalleDTO.getId_producto(), detalleDTO.getCantidad());
                }
                venta.setSubTotal(subTotal);
                venta.setTotal(total);
                venta = ventaRepository.save(venta);
            }
        }
        return venta;
    }
}