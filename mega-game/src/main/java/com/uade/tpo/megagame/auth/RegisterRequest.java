package com.uade.tpo.megagame.auth;

import com.uade.tpo.megagame.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nombre;
    private String telefono;
    private String mail;
    private String login;
    private String pass;
    private boolean flag_estado;
    private Role role;
}