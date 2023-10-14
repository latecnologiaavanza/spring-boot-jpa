package com.springboot.relaciones;

import com.springboot.relaciones.entities.entity7.Aerolinea;
import com.springboot.relaciones.entities.entity7.Vuelo;
import com.springboot.relaciones.repositories.repository7.AerolineaRepository;
import com.springboot.relaciones.repositories.repository7.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Prueba007 implements CommandLineRunner {

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @Autowired
    private VueloRepository vueloRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear una aerolínea con vuelos
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre("Aerolinea XYZ");

        Vuelo vuelo1 = new Vuelo();
        vuelo1.setNumeroVuelo("XYZ123");
        vuelo1.setDestino("Ciudad A");

        Vuelo vuelo2 = new Vuelo();
        vuelo2.setNumeroVuelo("XYZ456");
        vuelo2.setDestino("Ciudad B");

        aerolinea.getVuelos().add(vuelo1);
        aerolinea.getVuelos().add(vuelo2);

        aerolineaRepository.save(aerolinea);

        // Leer la aerolínea desde el repositorio
        Aerolinea aerolineaDesdeBD = aerolineaRepository.findById(aerolinea.getId()).orElse(null);
        System.out.println("Aerolinea: " + aerolineaDesdeBD.getNombre());

        // Eliminar la aerolínea (y los vuelos relacionados se eliminarán en cascada)
        aerolineaRepository.delete(aerolineaDesdeBD);

        // Comprobar que la aerolínea y los vuelos se han eliminado
        List<Aerolinea> aerolineas = aerolineaRepository.findAll();
        System.out.println("Numero de aerolineas en la base de datos: " + aerolineas.size());

        List<Vuelo> vuelos = vueloRepository.findAll();
        System.out.println("Numero de vuelos en la base de datos: " + vuelos.size());
    }
}
