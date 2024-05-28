package com.uade.tpo.megagame.entity.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductoDTO {
    private Long id_producto;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private LocalDate lanzamiento;
    private Boolean flag_destacar;
    private String desarrollador;
    private List<Long> compras_ids;
    private List<Long> tiposIds;
    private List<Long> generos_ids;
    private List<Long> plataformas_ids;
}

