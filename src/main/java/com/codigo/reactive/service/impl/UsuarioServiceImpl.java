package com.codigo.reactive.service.impl;

import com.codigo.reactive.controller.NotificationController;
import com.codigo.reactive.dto.UsuariosDTO;
import com.codigo.reactive.entity.Usuarios;
import com.codigo.reactive.repository.UsuarioRepository;
import com.codigo.reactive.service.UsuarioService;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final NotificationController notificationController;


    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, NotificationController notificationController) {
        this.usuarioRepository = usuarioRepository;
        this.notificationController = notificationController;
    }

    @Override
    public Mono<Usuarios> saveUser(UsuariosDTO usuariosDTO) {
        Usuarios person = new Usuarios();
        person.setName(usuariosDTO.getName());
        person.setEmail(usuariosDTO.getEmail());
        person.setState(usuariosDTO.getState());
        return usuarioRepository.save(person)
                .doOnSuccess(savedPerson -> notificationController.notifyNewPerson(usuariosDTO));
    }

    @Override
    public Mono<Usuarios> getUserById(String id) {
        return null;
    }
}
