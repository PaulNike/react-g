package com.codigo.reactive.controller;

import com.codigo.reactive.dto.UsuariosDTO;
import com.codigo.reactive.entity.Usuarios;
import com.codigo.reactive.service.ExcelProcessor;
import com.codigo.reactive.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/usuarios/v1")
@Log4j2
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuarioService personService;
    private final ReactiveKafkaProducerTemplate<String, UsuariosDTO> kafkaProducerTemplate;
    private final ExcelProcessor excelProcessorService;

    @PostMapping
    public Mono<ResponseEntity<Usuarios>> registerPerson(@RequestBody UsuariosDTO personDTO) {
        return personService.saveUser(personDTO)
                .map(savedPerson -> ResponseEntity.status(HttpStatus.CREATED).body(savedPerson));
    }

    @PostMapping("/upload")
    public Mono<ResponseEntity<Void>> uploadExcel(@RequestParam("file") MultipartFile file) {
        return excelProcessorService.processExcel(file)
                .flatMap(personDTO -> kafkaProducerTemplate.send("person-topic", personDTO))
                .then(Mono.just(ResponseEntity.status(HttpStatus.ACCEPTED).build()));
    }
}
