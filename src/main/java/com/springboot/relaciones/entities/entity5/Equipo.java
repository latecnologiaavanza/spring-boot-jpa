package com.springboot.relaciones.entities.entity5;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Jugador> jugadores = new ArrayList<>();

}
