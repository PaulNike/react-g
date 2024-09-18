package com.codigo.reactive.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/reactive/v1")
@Log4j2
public class EjemploController {

    // Simula la obtención de un usuario por ID
    @GetMapping("/user/{id}")
    public Mono<String> getUser(@PathVariable String id) {
        // Log para mostrar la entrada del método
        log.info("Iniciando la obtención del usuario con ID: {}", id);

        // Creando un Mono que emite un solo valor (el nombre del usuario) de forma asíncrona
        return Mono.just("User_" + id)
                .doOnNext(user -> log.info("Usuario encontrado: {}", user)) // Log del usuario encontrado
                .doFinally(signal -> log.info("Proceso completado con señal: {}", signal)); // Log al finalizar
    }

    // Simula la obtención de una lista de productos
    @GetMapping("/products")
    public Flux<String> getAllProducts() {
        log.info("Iniciando la obtención de todos los productos");

        // Lista simulada de productos
        List<String> products = List.of("Producto1", "Producto2", "Producto3", "Producto4");

        // Creando un Flux que emite los productos uno por uno
        return Flux.fromIterable(products)
                .doOnNext(product -> log.info("Producto encontrado: {}", product)) // Log de cada producto encontrado
                .doFinally(signal -> log.info("Proceso de productos completado con señal: {}", signal)); // Log al finalizar
    }
}
