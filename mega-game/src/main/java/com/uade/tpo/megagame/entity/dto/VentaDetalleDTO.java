package com.uade.tpo.megagame.entity.dto;

public class VentaDetalleDTO {
    private Long productoId;
    private int cantidad;
    public Long getProductoId(){
        return this.productoId;
    }
    public int getCantidad() {
        return this.cantidad;
    }

}
