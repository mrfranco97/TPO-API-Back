package com.uade.tpo.megagame.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private LocalDate lanzamiento;
    private String desarrollador;
    private Long tipo;
    private Integer stock;
}

