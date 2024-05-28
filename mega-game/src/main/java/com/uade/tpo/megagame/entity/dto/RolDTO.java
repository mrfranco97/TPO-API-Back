package com.uade.tpo.megagame.entity.dto;

import lombok.Data;
import java.util.List;

@Data
public class RolDTO {
    private Long idRol;
    private String nombre;
    private List<Long> usuariosIds;
}