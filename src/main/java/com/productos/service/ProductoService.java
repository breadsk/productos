package com.productos.service;

import com.productos.model.Producto;
import com.productos.repository.ProductoRepository;

import java.util.List;

//IMportamos anotaciones de Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProducts() {
        return productoRepository.getAllProducts();
    }

    public Producto getProductById(int id) {
        return productoRepository.getProductById(id);
    }

    public Producto saveProduct(Producto producto) {
        return productoRepository.saveProduct(producto);
    }

    public Producto updateProduct(Producto producto) {
        return productoRepository.updateProducto(producto);
    }

    public String deleteProduct(int id) {
        productoRepository.deleteProducto(id);
        return "Producto eliminado";
    }
}
