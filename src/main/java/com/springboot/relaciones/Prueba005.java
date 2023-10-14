package com.springboot.relaciones;

import com.springboot.relaciones.entities.entity5.Equipo;
import com.springboot.relaciones.entities.entity5.Jugador;
import com.springboot.relaciones.repositories.repository5.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
@Transactional
public class Prueba005 implements CommandLineRunner {

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear un equipo y jugadores
        Equipo equipo = new Equipo();
        equipo.setNombre("Equipo de Fútbol");

        Jugador jugador1 = new Jugador();
        jugador1.setNombre("Juan Pérez");
        jugador1.setNumeroCamiseta(10);

        Jugador jugador2 = new Jugador();
        jugador2.setNombre("María López");
        jugador2.setNumeroCamiseta(7);

        equipo.getJugadores().add(jugador1);
        equipo.getJugadores().add(jugador2);

        // Guardar el equipo (y automáticamente se guardarán los jugadores debido a CascadeType.MERGE)
        equipoRepository.save(equipo);

        // Leer el equipo y sus jugadores (usando FetchType.LAZY) dentro de la transacción
        Equipo equipoGuardado = equipoRepository.findById(equipo.getId()).orElse(null);
        if (equipoGuardado != null) {
            System.out.println("Equipo: " + equipoGuardado.getNombre());
            System.out.println("Jugadores en el equipo (usando FetchType.LAZY):");
            for (Jugador jugador : equipoGuardado.getJugadores()) {
                System.out.println("- " + jugador.getNombre() + " (Camiseta #" + jugador.getNumeroCamiseta() + ")");
            }
        }

        // Realizar operaciones de actualización y eliminación dentro de la misma transacción
        if (equipoGuardado != null) {
            // Actualizar un jugador en el equipo
            Jugador jugadorActualizado = equipoGuardado.getJugadores().get(0);
            jugadorActualizado.setNumeroCamiseta(11);

            // No es necesario guardar nuevamente el equipo, ya que las actualizaciones se aplican automáticamente debido a CascadeType.MERGE

            // Leer el equipo y sus jugadores nuevamente (dentro de la misma transacción)
            Equipo equipoActualizado = equipoRepository.findById(equipo.getId()).orElse(null);
            if (equipoActualizado != null) {
                System.out.println("Equipo actualizado: " + equipoActualizado.getNombre());
                System.out.println("Jugadores actualizados:");
                for (Jugador jugador : equipoActualizado.getJugadores()) {
                    System.out.println("- " + jugador.getNombre() + " (Camiseta #" + jugador.getNumeroCamiseta() + ")");
                }

                // Eliminar un jugador del equipo
                equipoActualizado.getJugadores().remove(1);

                // No es necesario guardar nuevamente el equipo, ya que las eliminaciones se aplican automáticamente debido a CascadeType.MERGE

                // Eliminar el equipo (y automáticamente se eliminarán los jugadores relacionados debido a CascadeType.MERGE)
                equipoRepository.delete(equipoActualizado);
            }
        }

        // Verificar si el equipo ha sido eliminado
        Equipo equipoEliminado = equipoRepository.findById(equipo.getId()).orElse(null);
        if (equipoEliminado == null) {
            System.out.println("Equipo eliminado con éxito.");
        }
    }

}
