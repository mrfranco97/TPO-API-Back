package com.uade.tpo.megagame.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.megagame.entity.Producto;
import com.uade.tpo.megagame.entity.Tipo;
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

    @GetMapping("/catalogo/tipo")
    public ResponseEntity<List<Tipo>> getTipo() {
        List<Tipo> result =  productoService.getTipo();
        if (result.size() > 0)
            return ResponseEntity.ok(result);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/catalogo/tipo/{tipoID}")
    public ResponseEntity<Tipo> getTipoByID(@PathVariable Long tipoID) {
        Optional<Tipo> result = tipoRepository.findById(tipoID);
        if (result.isPresent())
            return ResponseEntity.ok().body(result.get());
            
        return ResponseEntity.noContent().build();
    }
}
