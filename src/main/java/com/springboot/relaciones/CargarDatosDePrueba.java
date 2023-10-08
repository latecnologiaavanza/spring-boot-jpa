package com.springboot.relaciones;

import com.springboot.relaciones.entity.Direccion;
import com.springboot.relaciones.entity.Estudiante;
import com.springboot.relaciones.repository.DireccionRepository;
import com.springboot.relaciones.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosDePrueba implements CommandLineRunner {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public void run(String... args) throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan");

        Direccion direccion = new Direccion();
        direccion.setCalle("123 Calle Principal");
        direccion.setCiudad("Ciudad Ejemplo");
        direccion.setCodigoPostal("12345");

        estudiante.setDireccion(direccion);

        // Guardar el estudiante (y automáticamente se guardará la dirección)
        estudianteRepository.save(estudiante);
    }
}
