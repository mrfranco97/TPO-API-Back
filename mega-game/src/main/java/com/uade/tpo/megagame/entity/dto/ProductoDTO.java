package com.uade.tpo.megagame.entity.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id_producto;
    private String nombre;
    private String descripcion;
    private String imagen;
    private float precio;
    private Date lanzamiento; //de este no estoy seguro que sea asi
    private String desarrollador;
    private int flag_destacar;
}
