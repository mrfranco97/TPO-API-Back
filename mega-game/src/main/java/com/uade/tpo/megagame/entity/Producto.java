package com.uade.tpo.megagame.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Boolean flag_destacar;
    @Column
    private String desarrollador;
    //@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    //private List<VentaDetalle> compras;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ProductoTipo> tipos;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ProductoGenero> generos;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ProductoPlataforma> plataformas;
}