package com.productos.model;

//Importamos anotaciones de validaciones de Jakarta
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

//Importamos anotaciones de lombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data negera automaticamente
//getters,setters,toString,equela y hashCode
@Data

// @NoArgsConstructor genera el constructor vacio
@NoArgsConstructor

// @AllArgsConstructor genera el constructor con todos los atributos
@AllArgsConstructor

public class Producto {

    private Integer id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @Positive(message = "El stock debe ser mayor que cero")
    private int stock;
}
