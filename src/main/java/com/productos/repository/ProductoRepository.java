package com.productos.repository;

//Importamos el modelo de nuestro producto
import com.productos.model.Producto;

//Importamos clases para usar listas
import java.util.ArrayList;
import java.util.List;

//Importamos la anotacion repository
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository {

    // Lista en memoria que simula una base de datos.
    private List<Producto> listaProductos = new ArrayList<>();

    // Variable para simular el autoincremente del id;
    private Integer contadorId = 1;

    // Constructor del repositorio
    public ProductoRepository() {
        listaProductos.add(new Producto(contadorId++, "Teclado", 15000.0, 10));
        listaProductos.add(new Producto(contadorId++, "Mouse", 8000.0, 20));
        listaProductos.add(new Producto(contadorId++, "Monitor", 120000.0, 5));
    }

    // Metodo para retornar todos los productos
    // Simula un SELECT * FROM productos;
    public List<Producto> getAllProducts() {
        return listaProductos;
    }

    public Producto getProductById(int id) {
        for (Producto producto : listaProductos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public Producto saveProduct(Producto producto) {
        listaProductos.add(producto);
        return producto;
    }

    public Producto updateProducto(Producto pro) {
        int id = 0;
        int idPosicion = 0;

        // Primero vamos a buscar el producto
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == pro.getId()) {
                id = pro.getId();// Obtenemos el id del producto
                idPosicion = i;// Obtenemos la posicion del producto si se encontro
            }
        }

        Producto producto1 = new Producto();
        producto1.setId(id);
        producto1.setNombre(pro.getNombre());
        producto1.setPrecio(pro.getPrecio());
        producto1.setStock(pro.getStock());

        listaProductos.set(idPosicion, producto1);

        return producto1;
    }

    public void deleteProducto(int id) {
        // Alternativa 1
        // Primero vamos a buscar el libro
        Producto producto = getProductById(id);
        if (producto != null) {
            listaProductos.remove(producto);
        }

        // Alternativa 2
        int idPosicion = 0;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == id) {
                idPosicion = i;
                break;
            }
        }
        if (idPosicion > 0) {
            listaProductos.remove(idPosicion);
        }

        // Alternativa 3
        listaProductos.removeIf(x -> x.getId() == id);
    }

}
