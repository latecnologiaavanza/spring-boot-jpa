package com.springboot.relaciones.repositories.repository6;

import com.springboot.relaciones.entities.entity6.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
