package com.uade.tpo.megagame.entity.dto;

public class VentaDetalleDTO {
    private Long id_producto;
    private int cantidad;

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}