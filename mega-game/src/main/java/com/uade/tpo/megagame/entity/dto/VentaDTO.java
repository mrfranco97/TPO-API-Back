package com.uade.tpo.megagame.entity.dto;

import java.util.List;


public class VentaDTO {
    private Long id_usuario;
    private List<VentaDetalleDTO> detalles;

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public List<VentaDetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<VentaDetalleDTO> detalles) {
        this.detalles = detalles;
    }
}