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

    public Venta save(VentaDTO ventaDTO) {
        Venta venta = null;
        boolean ventaOk = true;

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(ventaDTO.getId_usuario());
        if (usuarioOptional.isPresent()) {
            venta = new Venta();
            venta.setUsuario(usuarioOptional.get());
            for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {
                Optional<Producto> productoOptional = productoService.getProductoById(detalleDTO.getId_producto());
                if (productoOptional.isPresent()) {
                    Producto producto = productoOptional.get();
                    if (producto.getStock() >= detalleDTO.getCantidad()){
                        VentaDetalle detalle = new VentaDetalle(detalleDTO.getCantidad());
                        detalle.setProducto(producto);
                        detalle.setVenta(venta);
                        venta.getDetalle().add(detalle);
                        
                    } else {
                        ventaOk = false;
                        venta = null;
                        break;
                    }
                } 
            }

            if (ventaOk){
                for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {
                    productoService.modificarStock(detalleDTO.getId_producto(), detalleDTO.getCantidad());
                }
                venta = ventaRepository.save(venta);
            }
        }
        return venta;
    }
}
