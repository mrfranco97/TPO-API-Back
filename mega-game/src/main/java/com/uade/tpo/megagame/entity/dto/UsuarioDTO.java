package com.uade.tpo.megagame.entity.dto;


import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id_usuario;
    private String nombre;
    private int telefono;
    private String mail;
    private String login;
    private String pass; //este hay qeu ver si despues lo codificamos con JWT
    private String rol;
    private int flag_estado;
}



