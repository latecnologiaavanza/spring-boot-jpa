package com.springboot.relaciones.repositories.repository7;

import com.springboot.relaciones.entities.entity7.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AerolineaRepository extends JpaRepository<Aerolinea,Long> {
}
