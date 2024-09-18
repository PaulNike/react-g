package com.codigo.reactive.service.impl;

import com.codigo.reactive.dto.UsuariosDTO;
import com.codigo.reactive.service.ExcelProcessor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Service
public class ExcelProcessorImpl implements ExcelProcessor {
    @Override
    public Flux<UsuariosDTO> processExcel(MultipartFile file) {
        return Flux.create(sink -> {
            try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    UsuariosDTO personDTO = new UsuariosDTO();
                    personDTO.setName(row.getCell(0).getStringCellValue());
                    personDTO.setEmail(row.getCell(1).getStringCellValue());
                    personDTO.setState(row.getCell(2).getStringCellValue());
                    sink.next(personDTO);
                }
                sink.complete();
            } catch (IOException e) {
                sink.error(e);
            }
        });
    }
}
