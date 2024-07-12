package com.uade.tpo.megagame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.megagame.entity.Venta;
import com.uade.tpo.megagame.entity.dto.VentaDTO;
import com.uade.tpo.megagame.interfaces.VentaInterface;

@RestController
public class VentasController {
    @Autowired
    private VentaInterface ventaService;

    @GetMapping("/ventas")
    public ResponseEntity<List<Venta>> getAll() {
        List<Venta> result = ventaService.findAll();
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/ventas/ByIdUsuario/{idCliente}")
    public ResponseEntity<List<Venta>> findByIdUsuario(@PathVariable Long idCliente) {
        List<Venta> result = ventaService.findByIdUsuario(idCliente);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/ventas")
    public ResponseEntity<?> createVenta(@RequestBody VentaDTO ventaDTO) {
        try {
            Venta savedVenta = ventaService.save(ventaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVenta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

        @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
    }
}