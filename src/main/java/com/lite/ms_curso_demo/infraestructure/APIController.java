package com.lite.ms_curso_demo.infraestructure;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class APIController {
    @Value("${app.mensaje}")
    private String mensaje;
    @Value("${app.factor}")
    private int factor;

    @GetMapping("/")
    public String getHello() {
        return "Hola Mundo!";
    }

    @PostMapping("/Add")
    public String postAdd() {
        return "Hola Mundo! - POST";
    }

    @GetMapping("/calculo/{valor}")
    public int calcularConValor(@PathVariable int valor) {
        return valor * factor;
    }

    @GetMapping("/hola")
    public String getMensaje() {
        return mensaje;
    }

}
