package com.springboot.relaciones;

import com.springboot.relaciones.entities.entity3.Editor;
import com.springboot.relaciones.entities.entity3.Revista;
import com.springboot.relaciones.repositories.repository3.EditorRepository;
import com.springboot.relaciones.repositories.repository3.RevistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
public class Prueba003 implements CommandLineRunner {

    @Autowired
    private EditorRepository editorRepository;

    @Autowired
    private RevistaRepository revistaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear un nuevo editor
        Editor editor = new Editor();
        editor.setNombre("Editorial XYZ");

        // Crear revistas y agregarlas al editor
        Revista revista1 = new Revista();
        revista1.setNombre("Revista A");
        revista1.setEditor(editor);

        Revista revista2 = new Revista();
        revista2.setNombre("Revista B");
        revista2.setEditor(editor);

        editor.getRevistas().add(revista1);
        editor.getRevistas().add(revista2);

        // Guardar el editor (y automáticamente se guardarán las revistas debido a CascadeType.ALL)
        editorRepository.save(editor);

        // Leer el editor recién guardado y sus revistas
        Optional<Editor> editorGuardado = editorRepository.findById(editor.getId());
        if (editorGuardado.isPresent()) {
            Editor editorObtenido = editorGuardado.get();
            System.out.println("Editor Obtenido: " + editorObtenido.getNombre());

            // Cargar la colección de revistas dentro de la transacción
            editorObtenido.getRevistas().size();

            // Actualizar el nombre del editor
            editorObtenido.setNombre("Editorial ABC");
            editorRepository.save(editorObtenido);
            System.out.println("Editor actualizado: " + editorObtenido.getNombre());

            // Crear una nueva revista
            Revista revista3 = new Revista();
            revista3.setNombre("Revista C");
            revista3.setEditor(editorObtenido);
            revistaRepository.save(revista3);
            System.out.println("Revista creada: " + revista3.getNombre());

            // Eliminar una revista
            Optional<Revista> revistaAEliminar = revistaRepository.findById(revista1.getId());
            if (revistaAEliminar.isPresent()) {
                Revista revistaEliminar = revistaAEliminar.get();
                revistaRepository.delete(revistaEliminar);
                System.out.println("Revista eliminada: " + revistaEliminar.getNombre());
            } else {
                System.out.println("Revista no encontrada.");
            }
        } else {
            System.out.println("Editor no encontrado.");
        }
    }
}
