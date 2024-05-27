package com.uade.tpo.megagame.entity.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductoDTO {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private LocalDate lanzamiento;
    private Boolean flagDestacar;
    private String desarrollador;
    private List<Long> comprasIds;
    private List<Long> tiposIds;
    private List<Long> generosIds;
    private List<Long> plataformasIds;
}