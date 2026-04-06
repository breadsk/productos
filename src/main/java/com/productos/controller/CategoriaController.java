package com.productos.controller;

import com.productos.model.Categoria;
import com.productos.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Listar todas las categorias
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<Categoria> categorias = categoriaService.getAllCategorias();

        Map<String, Object> response = new HashMap<>();
        if (categorias == null || categorias.isEmpty()) {
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.OK.value());
            response.put("message", "No hay categorías registradas");
            response.put("data", null);
            return ResponseEntity.ok(response);
        }
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Categorías encontradas");
        response.put("data", categorias);
        return ResponseEntity.ok(response);
    }

    // Obtener categorias por ID
    @GetMapping("{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {

        Categoria categoria = categoriaService.getCategoriaById(id);

        Map<String, Object> response = new HashMap<>();
        if (categoria == null) {
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "No se encontró la categoría con id: " + id);
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Categoría encontrada");
        response.put("data", categoria);
        return ResponseEntity.ok(response);

    }

    // Crear una categoria
    public ResponseEntity<?> createCategory(@Valid @RequestBody Categoria categoria) {

        // Evitar duplicados por nombres
        if (categoriaService.existsByNombre(categoria.getNombre())) {
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "Ya existe una categoría con el nombre: " + categoria.getNombre());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Categoria saved = categoriaService.saveCategoria(categoria);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Categoría creada exitosamente");
        response.put("data", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Actualizar categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Integer id, @Valid @RequestBody Categoria categoria) {
        Categoria existing = categoriaService.getCategoriaById(id);
        if (existing == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "No se encontró la categoría con id: " + id);
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Actualizar campos permitidos
        existing.setNombre(categoria.getNombre());
        existing.setDescripcion(categoria.getDescripcion());
        // No se actualiza la lista de productos aquí, solo datos básicos

        Categoria updated = categoriaService.saveCategoria(existing);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Categoría actualizada exitosamente");
        response.put("data", updated);
        return ResponseEntity.ok(response);
    }

    // Eliminar categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id) {
        Categoria existing = categoriaService.getCategoriaById(id);
        if (existing == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "No se encontró la categoría con id: " + id);
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Verificar si tiene productos asociados (opcional, para evitar eliminar con
        // dependencias)
        if (existing.getProductos() != null && !existing.getProductos().isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.CONFLICT.value());
            response.put("message", "No se puede eliminar la categoría porque tiene productos asociados");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        categoriaService.deleteCategoria(id);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Categoría eliminada exitosamente");
        response.put("data", null);
        return ResponseEntity.ok(response);
    }

}
