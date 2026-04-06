package com.productos.config;

import com.productos.model.Categoria;
import com.productos.model.Producto;
import com.productos.service.ProductoService;
import com.productos.service.CategoriaService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public void run(String... args) throws Exception {

        // Creamos categorias de prueba
        // Categiria Electronica
        // Categoría Electrónica (sin tilde)
        Categoria electronica = null;
        if (!categoriaService.existsByNombre("Electronica")) {
            electronica = new Categoria("Electronica", "Productos electrónicos, computadoras, smartphones",
                    new ArrayList<>());
            electronica = categoriaService.saveCategoria(electronica);
            System.out.println("✅ Categoría guardada: Electronica");
        } else {
            electronica = categoriaService.findByNombre("Electronica"); // ← mismo nombre
            System.out.println("⚠️ Categoría ya existente: Electronica");
        }

        // Categoría Accesorios (sin cambios, está bien)
        Categoria accesorios = null;
        if (!categoriaService.existsByNombre("Accesorios")) {
            accesorios = new Categoria(null, "Accesorios", "Periféricos, cables,adaptadores", new ArrayList<>());
            accesorios = categoriaService.saveCategoria(accesorios);
            System.out.println("✅ Categoría guardada: Accesorios");
        } else {
            accesorios = categoriaService.findByNombre("Accesorios");
            System.out.println("⚠️ Categoría ya existente: Accesorios");
        }

        // Categoría Hogar (sin cambios)
        Categoria hogar = null;
        if (!categoriaService.existsByNombre("Hogar")) {
            hogar = new Categoria(null, "Hogar", "Artículos para el hogar y cocina", new ArrayList<>());
            hogar = categoriaService.saveCategoria(hogar);
            System.out.println("✅ Categoría guardada: Hogar");
        } else {
            hogar = categoriaService.findByNombre("Hogar");
            System.out.println("⚠️ Categoría ya existente: Hogar");
        }

        // Categoría Hogar (sin cambios)
        Categoria oficina = null;
        if (!categoriaService.existsByNombre("Oficina")) {
            oficina = new Categoria(null, "Oficina", "Artículos para la oficina", new ArrayList<>());
            oficina = categoriaService.saveCategoria(oficina);
            System.out.println("✅ Categoría guardada: Oficina");
        } else {
            hogar = categoriaService.findByNombre("Oficina");
            System.out.println("⚠️ Categoría ya existente: Oficina");
        }

        // Creamos productos de prueba
        // Producto 1: Laptop Gamer (categoría Electrónica)
        if (!productoService.existsByNombre("Laptop Gamer")) {
            Producto laptop = new Producto("Laptop Gamer", 1200.99, 10);
            laptop.setCategoria(electronica);
            productoService.saveProduct(laptop);
            System.out.println("✅ Producto guardado: Laptop Gamer → Electrónica");
        } else {
            System.out.println("⚠️ Producto ya existente: Laptop Gamer");
        }

        // Producto 2: Mouse Inalámbrico (categoría Accesorios)
        if (!productoService.existsByNombre("Mouse Inalámbrico")) {
            Producto mouse = new Producto("Mouse Inalámbrico", 25.50, 50);
            mouse.setCategoria(accesorios);
            productoService.saveProduct(mouse);
            System.out.println("✅ Producto guardado: Mouse Inalámbrico → Accesorios");
        } else {
            System.out.println("⚠️ Producto ya existente: Mouse Inalámbrico");
        }

        // // Producto 3: Teclado Mecánico (categoría Accesorios)
        if (!productoService.existsByNombre("Teclado Mecánico")) {
            Producto teclado = new Producto("Teclado Mecánico", 75.00, 30);
            teclado.setCategoria(hogar);
            productoService.saveProduct(teclado);
            System.out.println("✅ Producto guardado: Teclado Mecánico → Accesorios");
        } else {
            System.out.println("⚠️ Producto ya existente: Teclado Mecánico");
        }

        // Producto 4: Sartén Antiadherente (categoría Hogar)
        if (!productoService.existsByNombre("Sartén Antiadherente")) {
            Producto sarten = new Producto("Sartén Antiadherente", 35.00, 20);
            sarten.setCategoria(oficina);
            productoService.saveProduct(sarten);
            System.out.println("✅ Producto guardado: Sartén Antiadherente → Hogar");
        } else {
            System.out.println("⚠️ Producto ya existente: Sartén Antiadherente");
        }
    }

}