package com.uade.tpo.megagame.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Genero {
    public Genero(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_genero;
   
    @Column
    private String nombre;
    
}
