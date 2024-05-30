package com.uade.tpo.megagame.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class TipoDTO {
    private Long idTipo;
    private String nombre;
    private List<Long> productosIds;
}