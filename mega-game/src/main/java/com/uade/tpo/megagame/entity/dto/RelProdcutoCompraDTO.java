package com.uade.tpo.megagame.entity.dto;

import lombok.Data;

@Data
public class RelProdcutoCompraDTO {
    private Long id_rel_pc;
    private int id_producto;
    private Float precio_producto;
    private int cantidad_producto; 
}
