package com.springboot.relaciones.entities.entity3;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Editor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    /*
    La anotación mappedBy se utiliza en JPA (Java Persistence API) para establecer
    una relación bidireccional entre entidades cuando se está mapeando una relación
    uno a muchos o muchos a uno. Esta anotación es importante porque define la entidad
    propietaria de la relación y la entidad inversa, y evita la duplicación de la
    relación en la base de datos.
    **/
    @OneToMany(mappedBy = "editor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Revista> revistas = new ArrayList<>();
}
