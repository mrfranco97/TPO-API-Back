package com.uade.tpo.megagame.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {
    public Usuario(){
    }

    public Usuario(String nombre){
        this.nombre=nombre;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column
    private String nombre;

    @Column
    private int telefono;

    @Column
    private String mail;

    @Column
    private String login;

    @Column
    private String pass;

    @Column
    private String rol;

    @Column
    private int flag_estado;
    
}
