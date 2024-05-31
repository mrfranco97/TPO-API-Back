package com.uade.tpo.megagame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.megagame.entity.Venta;
import com.uade.tpo.megagame.entity.dto.VentaDTO;
import com.uade.tpo.megagame.interfaces.VentaInterface;


@RestController
@RequestMapping("ventas")
public class VentasController {
    @Autowired
    private VentaInterface ventaService;

    @GetMapping()
    public ResponseEntity<List<Venta>> getAll() {
        List<Venta> result = ventaService.findAll();
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/ByIdUsuario/{idCliente}")
    public ResponseEntity<List<Venta>> findByIdUsuario(@PathVariable Long idCliente) {
        List<Venta> result = ventaService.findByIdUsuario(idCliente);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Ejemplo post
     * {
            "id_usuario": 1,
            "detalles": [
                {
                    "id_producto": 1,
                    "cantidad": 2
                },
                {
                    "id_producto": 2,
                    "cantidad": 1
                }
            ]
        }
     /* 
     @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody VentaDTO ventaDTO) {
        Venta venta = new Venta();
        for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {
            Optional<Producto> productoOptional = productoService.getProductoById(detalleDTO.getProductoId());
            if (productoOptional.isPresent()) {
                Producto producto = productoOptional.get();
                VentaDetalle detalle = new VentaDetalle(detalleDTO.getCantidad());
                detalle.setProducto(producto);
                //detalle.setVenta(venta);
                venta.getDetalle().add(detalle);
            }else {
                return ResponseEntity.badRequest().body(null);
            }
        }
        Venta savedVenta = ventaService.save(venta);
        return ResponseEntity.ok(savedVenta);
    }

*/
    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody VentaDTO ventaDTO) {
        Venta savedVenta = ventaService.save(ventaDTO);

        if (savedVenta == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(savedVenta);
    }
}
