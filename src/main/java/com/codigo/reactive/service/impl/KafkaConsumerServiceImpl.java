package com.codigo.reactive.service.impl;

import com.codigo.reactive.dto.UsuariosDTO;
import com.codigo.reactive.service.UsuarioService;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl  {
    private final ReactiveKafkaConsumerTemplate<String, UsuariosDTO> kafkaConsumerTemplate;
    private final UsuarioService personService;

    public KafkaConsumerServiceImpl(ReactiveKafkaConsumerTemplate<String, UsuariosDTO> kafkaConsumerTemplate, UsuarioService personService) {
        this.kafkaConsumerTemplate = kafkaConsumerTemplate;
        this.personService = personService;
        listenToKafkaTopic();
    }
    private void listenToKafkaTopic() {
        kafkaConsumerTemplate.receiveAutoAck()
                .flatMap(consumerRecord -> personService.saveUser(consumerRecord.value()))
                .doOnError(error -> {
                    // Imprimir el error para depuración
                    System.err.println("Error al procesar el mensaje de Kafka: " + error.getMessage());
                })
                .subscribe();
    }
}
