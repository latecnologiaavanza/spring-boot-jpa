package com.springboot.relaciones;

import com.springboot.relaciones.entities.entity8.Proyecto;
import com.springboot.relaciones.entities.entity8.Tarea;
import com.springboot.relaciones.repositories.repository8.ProyectoRepository;
import com.springboot.relaciones.repositories.repository8.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Prueba008 implements CommandLineRunner {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private TareaRepository tareaRepository;
    @Override
    public void run(String... args) throws Exception {
        // Crear un proyecto y tareas
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto A");

        Tarea tarea1 = new Tarea();
        tarea1.setNombre("Tarea 1");
        tarea1.setProyecto(proyecto);

        Tarea tarea2 = new Tarea();
        tarea2.setNombre("Tarea 2");
        tarea2.setProyecto(proyecto);

        proyecto.getTareas().add(tarea1);
        proyecto.getTareas().add(tarea2);

        proyectoRepository.save(proyecto);

        // Leer el proyecto desde el repositorio
        Proyecto proyectoDesdeBD = proyectoRepository.findById(proyecto.getId()).orElse(null);
        System.out.println("Proyecto: " + proyectoDesdeBD.getNombre());

        // Desprender el proyecto y sus tareas
        proyectoRepository.detach(proyectoDesdeBD);

        // Realizar cambios en las tareas
        for (Tarea tarea : proyectoDesdeBD.getTareas()) {
            tarea.setNombre(tarea.getNombre() + " (Modificado)");
        }

        // Guardar las tareas modificadas
        for (Tarea tarea : proyectoDesdeBD.getTareas()) {
            tareaRepository.save(tarea);
        }

        // Comprobar que las tareas se han modificado en la base de datos
        proyectoDesdeBD = proyectoRepository.findById(proyecto.getId()).orElse(null);
        System.out.println("Tareas modificadas:");
        for (Tarea tarea : proyectoDesdeBD.getTareas()) {
            System.out.println(tarea.getNombre());
        }
    }
}
