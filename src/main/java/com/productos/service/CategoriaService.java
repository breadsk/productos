package com.productos.service;

import com.productos.model.Categoria;
import com.productos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todas las categorias
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    // Obtener categoria por ID
    public Categoria getCategoriaById(Integer id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        return optional.orElse(null);
    }

    // Guardar o actualizar categoría
    @Transactional
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Eliminar categoria por ID
    @Transactional
    public void deleteCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }

    // Verificar si existe una categoria por nombre
    public boolean existsByNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }

    // Buscar categoria por nombre
    public Categoria fundByNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

}
