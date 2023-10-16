package com.springboot.relaciones.entities.entity8;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.DETACH)
    private List<Tarea> tareas;

    public Proyecto() {
        tareas = new ArrayList<>();
    }
}
