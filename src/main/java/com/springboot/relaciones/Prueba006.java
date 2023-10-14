package com.springboot.relaciones;

import com.springboot.relaciones.entities.entity6.OrdenCompra;
import com.springboot.relaciones.entities.entity6.Producto;
import com.springboot.relaciones.repositories.repository6.OrdenCompraRepository;
import com.springboot.relaciones.repositories.repository6.ProductoRepository;
import com.springboot.relaciones.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class Prueba006 implements CommandLineRunner {

    @Autowired
    private CompraService compraService;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Override
    public void run(String... args) throws Exception {
        // Operaciones CRUD de Producto
        // Simular una compra
        Long productoId = 14L; // ID del producto a comprar
        int cantidad = 2; // Cantidad de productos a comprar
        String metodoPago = "tarjeta_credito"; // Método de pago

        try {
            compraService.realizarCompra(productoId, cantidad, metodoPago);
            System.out.println("Compra realizada con éxito.");
        } catch (Exception e) {
            System.err.println("Error al realizar la compra: " + e.getMessage());
        }

        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Nuevo Producto");
        nuevoProducto.setPrecio(20.0);
        nuevoProducto.setCantidadDisponible(100);


        productoRepository.save(nuevoProducto);
        System.out.println("Nuevo producto creado: " + nuevoProducto.getId());

        Producto productoExistente = productoRepository.findById(productoId).orElse(null);

        if (productoExistente != null) {
            List<OrdenCompra> ordenesCompraAsociadas = ordenCompraRepository.findByProducto(productoExistente);
            for (OrdenCompra ordenCompra : ordenesCompraAsociadas) {
                ordenCompraRepository.delete(ordenCompra);
            }

            productoExistente.setPrecio(25.0);
            productoRepository.save(productoExistente);
            System.out.println("Producto actualizado: " + productoExistente.getId());

            productoRepository.delete(productoExistente);
            System.out.println("Producto eliminado: " + productoExistente.getId());
        }
    }
}
