package com.uade.tpo.megagame.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
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
    private String telefono;
    @Column
    private String mail;
    @Column
    private String login;
    @Column
    private String pass;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @Column
    private Boolean flag_estado;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;
}
