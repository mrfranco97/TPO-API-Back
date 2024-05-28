package com.uade.tpo.megagame.repository;
import com.uade.tpo.megagame.entity.Venta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query(value = "SELECT v FROM Venta v WHERE v.usuario = ?1")
    List<Venta> findByIdUsuario(Long usuario);
}