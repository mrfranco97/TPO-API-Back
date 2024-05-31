package com.uade.tpo.megagame.interfaces;
import com.uade.tpo.megagame.entity.Venta;
import com.uade.tpo.megagame.entity.dto.VentaDTO;

import java.util.List;
import java.util.Optional;

public interface VentaInterface {
    public List<Venta> findAll();

    public Optional<Venta> findById(Long id);

    public List<Venta> findByIdUsuario(Long clienteId);
    
    public Venta save(VentaDTO venta);
}
