package com.springboot.relaciones.services;

import com.springboot.relaciones.entities.entity6.OrdenCompra;
import com.springboot.relaciones.entities.entity6.Producto;
import com.springboot.relaciones.repositories.repository6.OrdenCompraRepository;
import com.springboot.relaciones.repositories.repository6.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompraService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private OrdenCompraRepository ordenRepository;

    @Autowired
    private PagoService pagoService;

    @Transactional
    public void realizarCompra(Long productoId, int cantidad, String metodoPago) {
        Producto producto = productoRepository.findById(productoId).orElse(null);

        if (producto != null && producto.getCantidadDisponible() >= cantidad) {
            // Intentar realizar el pago
            boolean pagoExitoso = pagoService.realizarPago(metodoPago, producto.getPrecio() * cantidad);

            if (pagoExitoso) {
                // Disminuir el inventario del producto
                producto.setCantidadDisponible(producto.getCantidadDisponible() - cantidad);
                productoRepository.save(producto);

                // Registrar la orden de compra
                OrdenCompra orden = new OrdenCompra();
                orden.setProducto(producto);
                orden.setCantidad(cantidad);
                orden.setTotal(producto.getPrecio() * cantidad);
                ordenRepository.save(orden);

                // Si todo se realiza con éxito, la transacción se confirma automáticamente.
            } else {
                // El pago no fue exitoso, puedes manejarlo de acuerdo a tus requisitos.
                throw new RuntimeException("Pago no exitoso");
            }
        } else {
            // Producto no disponible en inventario, puedes manejarlo de acuerdo a tus requisitos.
            throw new RuntimeException("Producto no disponible");
        }
    }

}
