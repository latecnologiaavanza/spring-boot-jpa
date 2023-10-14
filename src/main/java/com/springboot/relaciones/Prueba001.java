package com.springboot.relaciones;

import com.springboot.relaciones.entities.entity1.Direccion;
import com.springboot.relaciones.entities.entity1.Estudiante;
import com.springboot.relaciones.repositories.repository1.DireccionRepository;
import com.springboot.relaciones.repositories.repository1.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class Prueba001 implements CommandLineRunner {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public void run(String... args) throws Exception {
        Direccion direccion = new Direccion();
        direccion.setCalle("123 Calle Principal");
        direccion.setCiudad("Ciudad Ejemplo");
        direccion.setCodigoPostal("12345");

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan");
        estudiante.setDireccion(direccion);

        estudianteRepository.save(estudiante);

        // Actualizar un estudiante
        estudiante.setNombre("Juan Actualizado");
        estudianteRepository.save(estudiante);

        // Listar todos los estudiantes
        Iterable<Estudiante> estudiantes = estudianteRepository.findAll();
        for (Estudiante e : estudiantes) {
            System.out.println("Estudiante: " + e.getNombre() + ", Dirección: " + e.getDireccion().getCalle());
        }

        // Eliminar un estudiante
        estudianteRepository.delete(estudiante);
    }
}
