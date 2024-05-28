package com.uade.tpo.megagame.entity;

import java.time.LocalDate;
import java.util.List;

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
    private Long idProducto;

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
    private Boolean flagDestacar;

    @Column
    private String desarrollador;

    @OneToMany(mappedBy = "producto")
    private List<RelProductoCompra> compras;

    @OneToMany(mappedBy = "producto")
    private List<RelProductoTipo> tipos;

    @OneToMany(mappedBy = "producto")
    private List<RelProductoGenero> generos;

    @OneToMany(mappedBy = "producto")
    private List<RelProductoPlataforma> plataformas;

}