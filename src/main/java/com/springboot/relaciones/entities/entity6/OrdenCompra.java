package com.springboot.relaciones.entities.entity6;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Producto producto;

    private int cantidad;
    private double total;
}
