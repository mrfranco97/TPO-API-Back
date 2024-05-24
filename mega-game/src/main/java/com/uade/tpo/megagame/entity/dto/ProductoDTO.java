package com.uade.tpo.megagame.entity.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;

    public ProductoDTO(Long id,String nombre){
        this.id=id;
        this.nombre=nombre;
    }

        public ProductoDTO(String nombre){
        this.nombre=nombre;
        }

        public String getNombre(){
            return this.nombre;
        }
}
