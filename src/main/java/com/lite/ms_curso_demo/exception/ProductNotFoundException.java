package com.lite.ms_curso_demo.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String name) {
        super("Producto con la palabra clave " + name + " no encontrado");
    }

    public ProductNotFoundException(Long id) {
        super("Producto con id: " + id + " no encontrado");
    }

}