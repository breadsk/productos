package com.productos.model;

//Importamos anotaciones de validaciones de Jakarta
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

//Importaciones de anotaciones de JPA
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

//Importamos anotaciones de lombok
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    @Column(nullable = false)
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @Positive(message = "El stock debe ser mayor que cero")
    @Column(nullable = false)
    private int stock;

    // NUEVO CAMPO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Producto(String nombre, Double precio, int stock, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }
}

// ¿Qué hace fetch = FetchType.LAZY?
// Define la estrategia de carga de la categoría asociada a un producto:
// LAZY (carga perezosa o diferida): Cuando consultas un Producto (ej.
// findById),
// JPA no trae automáticamente los datos de su Categoria. Solo se cargará cuando
// accedas explícitamente al método getCategoria() dentro de una transacción
// activa.
// Esto ahorra recursos y mejora el rendimiento si no necesitas la categoría
// siempre.

// EAGER (carga ansiosa o inmediata): Traería la categoría junto con el producto
// en la misma consulta (usando un JOIN). Puede causar consultas pesadas
// innecesarias
// si no usas la categoría.