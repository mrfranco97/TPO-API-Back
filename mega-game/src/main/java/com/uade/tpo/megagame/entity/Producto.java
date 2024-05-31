package com.uade.tpo.megagame.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Producto {  
    public Producto(String nombre,String descripcion,String imagen,Double precio,LocalDate lanzamiento,String desarrolador,Tipo tipo,Integer stock){
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
    private String imagen;
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

}