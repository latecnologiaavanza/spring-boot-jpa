package com.springboot.relaciones;

import com.springboot.relaciones.entities.entity4.Automovil;
import com.springboot.relaciones.entities.entity4.Propietario;
import com.springboot.relaciones.repositories.repository4.AutomovilRepository;
import com.springboot.relaciones.repositories.repository4.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class Prueba004 implements CommandLineRunner {

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Autowired
    private AutomovilRepository automovilRepository;


    @Override
    public void run(String... args) throws Exception {
        // Crear un propietario
        Propietario propietario = new Propietario();
        propietario.setNombre("Juan Pérez");

        // Crear un automóvil y establecer la relación bidireccional
        Automovil automovil = new Automovil();
        automovil.setMarca("Toyota");
        automovil.setModelo("Camry");

        propietario.setAutomovil(automovil);
        automovil.setPropietario(propietario);

        // Guardar el propietario y el automóvil
        propietarioRepository.save(propietario);
        automovilRepository.save(automovil);

        // Leer un propietario por ID
        Long propietarioId = propietario.getId();
        Propietario propietarioLeido = propietarioRepository.findById(propietarioId).orElse(null);
        if (propietarioLeido != null) {
            System.out.println("Propietario leído: " + propietarioLeido.getNombre());
        }

        // Actualizar el automóvil asociado al propietario
        Automovil automovilActualizado = propietario.getAutomovil();
        automovilActualizado.setMarca("Honda");
        automovilActualizado.setModelo("Accord");
        automovilRepository.save(automovilActualizado);

        // Eliminar el propietario y su automóvil
        propietarioRepository.delete(propietario);

        // Verificar si el propietario ha sido eliminado
        Propietario propietarioEliminado = propietarioRepository.findById(propietarioId).orElse(null);
        if (propietarioEliminado == null) {
            System.out.println("Propietario eliminado con éxito.");
        }
    }
}
