package com.uade.tpo.megagame.entity;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Blob;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Producto {  
    public Producto(String nombre,String descripcion,Blob imagen,Double precio,LocalDate lanzamiento,String desarrolador,Tipo tipo,Integer stock){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.imagen=imagen;
        this.precio=precio;
        this.lanzamiento=lanzamiento;
        this.desarrollador=desarrolador;
        this.tipo=tipo;
        this.stock=stock;
    }  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    @JsonIgnore
    @Lob
    private Blob imagen;
    @Column
    private Double precio;
    @Column
    private LocalDate lanzamiento;
    @Column
    private Boolean flag_destacar=false;
    @Column
    private String desarrollador;
    @Column
    private int stock;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_tipo", referencedColumnName = "id", nullable = false)
    private Tipo tipo;

    @JsonProperty("imagen")
    public byte[] getImagenBytes() {
        if (this.imagen != null) {
            try {
                int blobLength = (int) imagen.length();
                byte[] blobBytes = imagen.getBytes(1, blobLength);
                return blobBytes;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}