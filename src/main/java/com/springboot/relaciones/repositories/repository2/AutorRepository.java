package com.springboot.relaciones.repositories.repository2;


import com.springboot.relaciones.entities.entity2.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
