package com.uade.tpo.megagame.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Producto {
        public Producto(){
    }

    public Producto(String nombre){
        this.nombre=nombre;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @Column
    private String nombre;
    
    @Column
    private String descripcion;

    @Column
    private String imagen;

    @Column
    private float precio;

    @Column
    private Date lanzamiento; //de este no estoy seguro que sea asi

    @Column
    private String desarrollador;

    @Column
    private int flag_destacar;

}