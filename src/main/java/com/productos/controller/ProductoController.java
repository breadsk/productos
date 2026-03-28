package com.productos.controller;

import com.productos.model.Producto;
import com.productos.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<Producto> productos = productoService.getProducts();

        Map<String, Object> response = new HashMap<>();
        if (productos == null || productos.isEmpty()) {
            response.put("timeStamp", LocalDateTime.now());
            response.put("status", HttpStatus.OK.value());
            response.put("message", "No hay productos registrados");
            response.put("data", null);// Es mejor enviar lista vacia que null

            return ResponseEntity.ok(response);
        }

        response.put("timeStamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("data", productos);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Producto producto = productoService.getProductById(id);

        Map<String, Object> response = new HashMap<>();

        if (producto == null) {
            response.put("timeStamp", LocalDateTime.now());
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "No se encontró el libro con id: " + id);
            response.put("data", null);

            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        }

        response.put("timeStamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Producto encontrado");
        response.put("data", producto);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Producto producto) {
        Producto productSaved = productoService.saveProduct(producto);

        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDateTime.now());
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Producto guardado satisfactoriamente");
        response.put("data", productSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @Valid @RequestBody Producto producto) {
        // Verificar si el producto existe antes de actualizar
        Producto existing = productoService.getProductById(id);
        if (existing == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("timeStamp", LocalDateTime.now());
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "No se encontró el producto con id: " + id);
            response.put("data", null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Producto updatedProduct = productoService.updateProduct(producto);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Producto actualizado exitosamente");
        response.put("data", updatedProduct);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        // Verificar si el producto existe antes de eliminar
        Producto existing = productoService.getProductById(id);
        if (existing == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "No se encontró el producto con id: " + id);
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Si existe, proceder con la eliminación
        productoService.deleteProduct(id);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Producto eliminado exitosamente");
        response.put("data", null); // No se devuelve el producto eliminado, pero se podría enviar el id si se desea

        return ResponseEntity.ok(response);
    }

}
