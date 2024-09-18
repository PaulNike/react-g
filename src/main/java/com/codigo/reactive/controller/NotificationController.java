package com.codigo.reactive.controller;

import com.codigo.reactive.dto.UsuariosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyNewPerson(UsuariosDTO personDTO) {
        messagingTemplate.convertAndSend("/topic/persons", personDTO);
    }
}
