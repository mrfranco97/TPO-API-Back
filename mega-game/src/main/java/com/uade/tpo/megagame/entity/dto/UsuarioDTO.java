package com.uade.tpo.megagame.entity.dto;

import com.uade.tpo.megagame.entity.Role;

import lombok.Data;

@Data
@Enumerated

public class UsuarioDTO {
    private Long idUsuario;
    private String nombre;
    private String telefono;
    private String mail;
    private String login;
    private String pass;
    private String idRol;
    private Role role;
    private Boolean flag_estado;
}
