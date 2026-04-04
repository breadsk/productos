package com.productos.repository;

import com.productos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // Método derivado para buscar por nombre ( útil para evitar duplicados )
    boolean existsByNombre(String nombre);

    // Opcional: buscar categoria por nombre
    Categoria findByNombre(String nombre);
}
