package com.productos.service;

import com.productos.model.Producto;
import com.productos.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

//IMportamos anotaciones de Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProducts() {
        return productoRepository.findAll();
    }

    public Producto getProductById(int id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElseThrow(() -> new RuntimeException("Paciente no encontrado con ese ID: " + id));
    }

    public Producto saveProduct(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProduct(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProduct(int id) {        
        productoRepository.deleteById(id);
    }
}
