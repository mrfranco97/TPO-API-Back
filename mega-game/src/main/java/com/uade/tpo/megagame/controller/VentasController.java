package com.uade.tpo.megagame.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.entity.Role;
import com.uade.tpo.megagame.entity.Usuario;
import com.uade.tpo.megagame.entity.Venta;
import com.uade.tpo.megagame.entity.VentaDetalle;
import com.uade.tpo.megagame.entity.dto.VentaDTO;
import com.uade.tpo.megagame.entity.dto.VentaDetalleDTO;
import com.uade.tpo.megagame.interfaces.ProductoInterface;
import com.uade.tpo.megagame.interfaces.UsuarioInterface;
import com.uade.tpo.megagame.interfaces.VentaInterface;


@RestController
@RequestMapping("ventas")
public class VentasController {
    @Autowired
    private VentaInterface ventaService;

    @Autowired
    private ProductoInterface productoService;

    @Autowired
    private UsuarioInterface usuarioService;

    // Este endpoint requiere el rol ADMIN
    @GetMapping()
    public ResponseEntity<List<Venta>> getAll(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.ADMIN.name()))) {
            List<Venta> result = ventaService.findAll();
            if (!result.isEmpty()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    // Este endpoint requiere el rol USER
    @GetMapping("/ByIdUsuario/{idCliente}")
    public ResponseEntity<List<Venta>> findByIdUsuario(@PathVariable Long idCliente, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.USER.name()))) {
            List<Venta> result = ventaService.findByIdUsuario(idCliente);
            if (!result.isEmpty()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    // Este endpoint requiere el rol ADMIN
    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody VentaDTO ventaDTO, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.ADMIN.name()))) {
            Venta venta = new Venta();
            
            Optional<Usuario> usuarioOptional = usuarioService.findById(ventaDTO.getId_usuario());
            if (usuarioOptional.isPresent()) {
                venta.setUsuario(usuarioOptional.get());
            } else {
                return ResponseEntity.badRequest().body(null);
            }

            for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {
                Optional<Producto> productoOptional = productoService.getProductoById(detalleDTO.getId_producto());
                if (productoOptional.isPresent()) {
                    Producto producto = productoOptional.get();
                    VentaDetalle detalle = new VentaDetalle(detalleDTO.getCantidad());
                    detalle.setProducto(producto);
                    detalle.setVenta(venta);
                    venta.getDetalle().add(detalle);
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }

            Venta savedVenta = ventaService.save(venta);
            return ResponseEntity.ok(savedVenta);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
