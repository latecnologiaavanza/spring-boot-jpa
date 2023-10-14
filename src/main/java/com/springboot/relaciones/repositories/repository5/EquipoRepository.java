package com.springboot.relaciones.repositories.repository5;

import com.springboot.relaciones.entities.entity5.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo,Long> {
}
