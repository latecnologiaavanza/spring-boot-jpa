package com.springboot.relaciones.entities.entity7;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroVuelo;
    private String destino;

    @ManyToOne
    @JoinColumn(name = "aerolinea_id")
    private Aerolinea aerolinea;

}
