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
public class Compras {
    public Compras() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_compra;

    @Column
    private int id_usuario;

    @Column
    private Date fecha;

    @Column
    private int flag_estado;

}