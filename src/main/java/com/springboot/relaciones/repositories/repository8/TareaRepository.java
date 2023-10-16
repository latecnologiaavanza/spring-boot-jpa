package com.springboot.relaciones.repositories.repository8;

import com.springboot.relaciones.entities.entity8.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long> {
}
