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
        // Crear una dirección
        Direccion direccion = new Direccion();
        direccion.setCalle("123 Calle Principal");
        direccion.setCiudad("Ciudad Ejemplo");
        direccion.setCodigoPostal("12345");
        direccionRepository.save(direccion);

        // Crear un estudiante asociado a la dirección
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan");
        estudiante.setDireccion(direccion);
        estudianteRepository.save(estudiante);
    }
}
