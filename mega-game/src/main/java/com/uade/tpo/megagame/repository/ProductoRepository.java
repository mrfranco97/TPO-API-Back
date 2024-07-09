package com.uade.tpo.megagame.repository;
import com.uade.tpo.megagame.entity.Producto;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("select p from Producto p where p.nombre = :nombre")
    List<Producto> findByName(String nombre);

    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:nombre%")
    List<Producto> findLikeName(String nombre);

    @Query("SELECT p FROM Producto p WHERE p.flag_destacar = true")
    List<Producto> findDestacados();
    
    @Modifying
    @Transactional
    @Query("UPDATE Producto p SET p.stock = p.stock - :cantidad WHERE p.id = :idProducto")
    int modificarStock(@Param("idProducto") Long idProducto, @Param("cantidad") int cantidad);

}