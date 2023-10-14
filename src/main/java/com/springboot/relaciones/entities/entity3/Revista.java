package com.springboot.relaciones.entities.entity3;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Revista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "editor_id")
    private Editor editor;

}
