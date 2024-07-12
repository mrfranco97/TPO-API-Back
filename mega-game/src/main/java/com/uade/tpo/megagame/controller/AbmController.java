package com.uade.tpo.megagame.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AbmController {
    @Autowired
    private ProductoInterface productoService;

    @Autowired
    private TipoRepository tipoRepository;

    @PostMapping(value = "/abm", consumes = "multipart/form-data")
    public ResponseEntity<Object> createProducto(@ModelAttribute ProductoDTO productodto) {
        try {
            Producto result = productoService.createProducto(
                productodto.getNombre(),
                productodto.getDescripcion(),
                productodto.getImagen(),
                productodto.getPrecio(),
                productodto.getDescuento(),
                productodto.getLanzamiento(),
                productodto.getDesarrollador(),
                productodto.getTipo(),
                productodto.getStock(),
                productodto.getFlag_destacar()
                );
            return ResponseEntity.created(URI.create("/catalogo/" + result.getId())).body(result);
        } catch (ProductoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\":\"Producto duplicado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"Error interno del servidor\"}");
        }
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

    @PutMapping("/abm")
    public ResponseEntity<Producto> modificarProducto(@RequestBody ProductoDTO modificacion) {
        Optional<Producto> result = productoService.getProductoById(modificacion.getId());
        if (result.isPresent()) {
            Optional<Tipo> consulta = tipoRepository.findById(modificacion.getTipo());
            if (consulta.isPresent()) {
                Tipo tipo = consulta.get();
                Producto productoExistente = result.get();
                productoExistente.setNombre(modificacion.getNombre());
                productoExistente.setDescripcion(modificacion.getDescripcion());
                //productoExistente.setImagen(modificacion.getImagen());
                productoExistente.setPrecio(modificacion.getPrecio());
                productoExistente.setDescuento(modificacion.getDescuento());
                productoExistente.setLanzamiento(modificacion.getLanzamiento());
                productoExistente.setDesarrollador(modificacion.getDesarrollador());
                productoExistente.setTipo(tipo);
                productoExistente.setStock(modificacion.getStock());
                productoExistente.setFlag_destacar(modificacion.getFlag_destacar());
                Producto productoActualizado = productoService.modificarProducto(productoExistente);
                return ResponseEntity.ok(productoActualizado);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
