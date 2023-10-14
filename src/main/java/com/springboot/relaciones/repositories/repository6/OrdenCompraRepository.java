package com.springboot.relaciones.repositories.repository6;

import com.springboot.relaciones.entities.entity6.OrdenCompra;
import com.springboot.relaciones.entities.entity6.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra,Long> {

    List<OrdenCompra> findByProducto(Producto producto);

}
