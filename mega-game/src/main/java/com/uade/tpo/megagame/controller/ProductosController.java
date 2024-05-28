package com.uade.tpo.megagame.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.entity.dto.ProductoDTO;
import com.uade.tpo.megagame.exception.ProductoDuplicadoException;
import com.uade.tpo.megagame.interfaces.ProductoInterface;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("catalogo")
public class ProductosController {
    @Autowired
    private ProductoInterface productoService;

    @GetMapping
    public ResponseEntity<Page<Producto>> getProductos(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productoService.getProductos(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productoService.getProductos(PageRequest.of(page, size)));
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long productoId) {
        Optional<Producto> result = productoService.getProductoById(productoId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createProducto(@RequestBody ProductoDTO productodto)
            throws ProductoDuplicadoException {
        Producto result = productoService.createProducto(productodto.getNombre());
        return ResponseEntity.created(URI.create("/catalogo/" + result.getId())).body(result);
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable Long productoId){
        Optional<Producto>result=productoService.getProductoById(productoId);
        if(result.isPresent()){
            productoService.eliminarProducto(productoId);
            return ResponseEntity.ok(result.get());
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<Producto> modificarProducto(@PathVariable Long productoId, @RequestBody ProductoDTO modificacion) {
        Optional<Producto>result=productoService.getProductoById(productoId);
        if(result.isPresent()){
            Producto modificado = new Producto(modificacion.getNombre());
            modificado.setId(productoId);
            return ResponseEntity.ok(productoService.modificarProducto(modificado));
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

}
