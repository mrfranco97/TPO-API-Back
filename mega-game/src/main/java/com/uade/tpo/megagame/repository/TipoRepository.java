package com.uade.tpo.megagame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.megagame.entity.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo,Long>{

}
