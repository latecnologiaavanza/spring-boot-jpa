package com.springboot.relaciones.entities.entity5;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int numeroCamiseta;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

}
