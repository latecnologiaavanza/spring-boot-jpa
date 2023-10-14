package com.springboot.relaciones.repositories.repository2;

import com.springboot.relaciones.entities.entity2.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Long> {
}
