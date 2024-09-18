package com.codigo.reactive.service;

import com.codigo.reactive.dto.UsuariosDTO;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

public interface ExcelProcessor {
    Flux<UsuariosDTO> processExcel(MultipartFile file);
}
