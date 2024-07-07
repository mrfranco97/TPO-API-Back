package com.uade.tpo.megagame.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
        this.cantidad = cantidad;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id", nullable = false)
    private Producto producto;

    @Column
    private Double precio;

    @Column
    private Float descuento;

    @Column
    private Integer cantidad;

    public Double getSubTotal(){
        return cantidad * producto.getPrecio();
    }

    public void setProducto(Producto producto){
        this.producto = producto;
        this.precio = producto.getPrecio();
    }
}
