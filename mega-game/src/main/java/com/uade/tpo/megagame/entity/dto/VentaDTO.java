package com.uade.tpo.megagame.entity.dto;

import java.util.List;

public class VentaDTO {
    private Long idCliente;
    private List<VentaDetalleDTO> detalles;
    
    public List<VentaDetalleDTO> getDetalles() {
        return this.detalles;
    }

    public Long getIdCliente() {
        return this.idCliente;
    }
}