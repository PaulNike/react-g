package com.codigo.reactive.repository;

import com.codigo.reactive.entity.Usuarios;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UsuarioRepository extends ReactiveCrudRepository<Usuarios,Long> {

}
