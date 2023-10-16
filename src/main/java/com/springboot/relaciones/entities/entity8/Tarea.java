package com.springboot.relaciones.entities.entity8;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

}
