package com.uade.tpo.megagame.entity.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CompraDTO {
    private Long idCompra;
    private UsuarioDTO usuario;
    private LocalDate fecha;
    private Boolean flagEstado;
    private List<Long> productosIds;
}
