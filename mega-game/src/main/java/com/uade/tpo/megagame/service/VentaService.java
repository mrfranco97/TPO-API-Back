package com.uade.tpo.megagame.service;
import com.uade.tpo.megagame.entity.Venta;
import java.util.List;
import java.util.Optional;

public interface VentaService {
    public List<Venta> findAll();
    public Optional<Venta> findById(Long id);
    public List<Venta> findByIdCliente(Long clienteId);
    public Venta save(Venta venta);
}
