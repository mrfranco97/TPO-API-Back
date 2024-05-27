package com.uade.tpo.megagame.entity.dto;

import lombok.Data;

@Data
public class RelProductoCompraDTO {
    private Long idRelPc;
    private Long compraId;
    private Long productoId;
    private Float precioProducto;
    private Integer cantidadProducto;
}
