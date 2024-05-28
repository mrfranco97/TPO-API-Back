package com.uade.tpo.megagame.entity.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ComprasDTO {
    private Long id_compra;
    private int id_usuario;
    private Date fecha;
    private int flag_estado;
    
}
