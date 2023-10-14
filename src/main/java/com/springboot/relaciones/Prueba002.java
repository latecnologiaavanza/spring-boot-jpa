package com.springboot.relaciones;

import com.springboot.relaciones.entities.entity2.Autor;
import com.springboot.relaciones.entities.entity2.Libro;
import com.springboot.relaciones.repositories.repository2.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component
public class Prueba002 implements CommandLineRunner {

    @Autowired
    private AutorRepository autorRepository;


    @Override
    public void run(String... args) throws Exception {
        // Crear un autor
        Autor autor = new Autor();
        autor.setNombre("J.K. Rowling");

        // Crear libros y agregarlos al autor
        Libro libro1 = new Libro();
        libro1.setTitulo("Harry Potter y la Piedra Filosofal");
        Libro libro2 = new Libro();
        libro2.setTitulo("Harry Potter y la Cámara Secreta");

        autor.getLibros().add(libro1);
        autor.getLibros().add(libro2);

        // Guardar el autor (y automáticamente se guardarán los libros debido a CascadeType.ALL)
        autorRepository.save(autor);

        // Leer el autor por ID
        Optional<Autor> autorGuardado = autorRepository.findById(autor.getId());
        autorGuardado.ifPresent(a -> {
            System.out.println("Autor leído por ID: " + a.getNombre());

            // Actualizar el autor
            a.setNombre("Nuevo Nombre del Autor");
            autorRepository.save(a);
            System.out.println("Autor actualizado: " + a.getNombre());
        });

        // Listar todos los autores
        List<Autor> autores = autorRepository.findAll();
        System.out.println("Lista de autores:");
        for (Autor a : autores) {
            System.out.println("- " + a.getNombre());
        }

        // Eliminar el autor
        autorRepository.delete(autor);
        System.out.println("Autor eliminado.");
    }
}
