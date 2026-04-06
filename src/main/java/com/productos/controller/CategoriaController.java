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

}
