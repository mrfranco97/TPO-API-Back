package com.uade.tpo.megagame.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.megagame.entity.Venta;
import com.uade.tpo.megagame.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> findByIdCliente(Long idCliente) {
        return ventaRepository.findByIdCliente(idCliente);
    }

    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

}
