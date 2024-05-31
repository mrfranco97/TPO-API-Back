package com.uade.tpo.megagame.repository;
import com.uade.tpo.megagame.entity.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = "select p from Producto p where p.nombre = ?1")
    List<Producto> findByName(String nombre);
    //List<Producto> findByTipo(Long tipo);
}