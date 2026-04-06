package com.productos.config;

import com.productos.model.Producto;
import com.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductoService productoService;

    @Override
    public void run(String... args) throws Exception {

        // Tres productos de ejemplo
        Producto[] productosEjemplo = {
                new Producto("Laptop Gamer", 1200.99, 10),
                new Producto("Mouse Inalámbrico", 25.50, 50),
                new Producto("Teclado Mecánico", 75.00, 30)
        };

        for (Producto p : productosEjemplo) {
            // Verificar si ya existe por nombre ( necesitas el método en service/repo)
            if (!productoService.existsByNombre(p.getNombre())) {
                productoService.saveProduct(p);
                System.out.println("Producto guardado: " + p.getNombre());
            } else {
                System.out.println("Producto ya existente: " + p.getNombre());
            }
        }

    }
}
