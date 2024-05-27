package com.uade.tpo.megagame.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Venta")
public class Venta{
    public Venta(){
    }

    public Venta(Long idcliente){
        this.idCliente=idcliente;
        this.fecha_insert = LocalDate.now();
        this.detalle = new ArrayList<>();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idCliente;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<VentaDetalle> detalle;

    @Column(name = "fecha_insert")
    private LocalDate fecha_insert;
}
