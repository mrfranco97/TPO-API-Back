package com.uade.tpo.megagame.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class RelProductoCompra {
     public RelProductoCompra(){
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rel_pc;
   
    @Column
    private int id_producto;

    @Column
    private Float precio_producto;

    @Column
    private int cantidad_producto; 
 
}
