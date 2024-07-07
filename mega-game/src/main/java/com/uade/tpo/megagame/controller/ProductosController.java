package com.uade.tpo.megagame.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
            return ResponseEntity.ok().body(result.get());
            //return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/catalogo/nombre/{productoNombre}")
    public ResponseEntity<List<Producto>> getProductosByNombre(@PathVariable String productoNombre) {
        List<Producto> result = productoService.getProductoByNombre(productoNombre);
        if (result.size() > 0)
            return ResponseEntity.ok(result);

        return ResponseEntity.noContent().build();
    }

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
                productodto.getStock());
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
            productoExistente.setDescuento(modificacion.getDescuento());
            productoExistente.setLanzamiento(modificacion.getLanzamiento());
            productoExistente.setDesarrollador(modificacion.getDesarrollador());
            productoExistente.setTipo(tipo);
            productoExistente.setStock(modificacion.getStock());
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
