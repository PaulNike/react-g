package com.codigo.reactive.service;

import com.codigo.reactive.dto.UsuariosDTO;
import com.codigo.reactive.entity.Usuarios;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface UsuarioService {
    Mono<Usuarios> saveUser(UsuariosDTO usuariosDTO);
    Mono<Usuarios> getUserById(String id);
}
