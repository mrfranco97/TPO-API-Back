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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.entity.Tipo;
import com.uade.tpo.megagame.entity.dto.ProductoDTO;
import com.uade.tpo.megagame.exception.ProductoDuplicadoException;
import com.uade.tpo.megagame.interfaces.ProductoInterface;
import com.uade.tpo.megagame.repository.TipoRepository;

@RestController
public class ProductosController {
    @Autowired
    private ProductoInterface productoService;

    @Autowired
    private TipoRepository tipoRepository;

    @GetMapping("/catalogo")
    public ResponseEntity<Page<Producto>> getProductos(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productoService.getProductos(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productoService.getProductos(PageRequest.of(page, size)));
    }

    @GetMapping("/catalogo/{productoId}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long productoId) {
        Optional<Producto> result = productoService.getProductoById(productoId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/abm")
    public ResponseEntity<Object> createProducto(@RequestBody ProductoDTO productodto)
            throws ProductoDuplicadoException {
        Producto result = productoService.createProducto(
            productodto.getNombre(),
            productodto.getDescripcion(),
            productodto.getImagen(),
            productodto.getPrecio(),
            productodto.getLanzamiento(),
            productodto.getDesarrollador(),
            productodto.getTipo(),
            productodto.getStock());
        return ResponseEntity.created(URI.create("/catalogo/" + result.getId())).body(result);
    }

    @DeleteMapping("/abm/{productoId}")
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

@PutMapping("/abm/{productoId}")
public ResponseEntity<Producto> modificarProducto(@PathVariable Long productoId, @RequestBody ProductoDTO modificacion) {
    Optional<Producto> result = productoService.getProductoById(productoId);
    if (result.isPresent()) {
        Optional<Tipo> consulta = tipoRepository.findById(modificacion.getTipo());
        if (consulta.isPresent()) {
            Tipo tipo = consulta.get();
            Producto productoExistente = result.get();
            productoExistente.setNombre(modificacion.getNombre());
            productoExistente.setDescripcion(modificacion.getDescripcion());
            productoExistente.setImagen(modificacion.getImagen());
            productoExistente.setPrecio(modificacion.getPrecio());
            productoExistente.setLanzamiento(modificacion.getLanzamiento());
            productoExistente.setDesarrollador(modificacion.getDesarrollador());
            productoExistente.setTipo(tipo);
            productoExistente.setStock(modificacion.getStock());
            Producto productoActualizado = productoService.modificarProducto(productoExistente);
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.badRequest().build(); // Si el tipo no existe, devuelve una respuesta de error
        }
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
