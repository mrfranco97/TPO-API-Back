package com.uade.tpo.megagame.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "VentaDetalle")
public class VentaDetalle {
    public VentaDetalle() {
 
    }
    public VentaDetalle(int cantidad)
    {
        this.fecha_insert = LocalDate.now();
        this.cantidad = cantidad;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false)
    private Producto producto;
    
    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "fecha_insert", nullable = false)
    private LocalDate fecha_insert;

}
