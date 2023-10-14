package com.springboot.relaciones.entities.entity7;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Aerolinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "aerolinea", cascade = CascadeType.REMOVE)
    private List<Vuelo> vuelos;

    public Aerolinea() {
        this.vuelos = new ArrayList<>();
    }
}
