package com.cibertec.FarmaSalud.business.api.controller;

import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoResponseDto;
import com.cibertec.FarmaSalud.business.domain.service.MedicamentoService;
import com.cibertec.FarmaSalud.business.domain.service.impl.UploadFileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @Autowired
    private UploadFileService uploadService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<MedicamentoResponseDto> listar() {
        return service.listarTodos();
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public MedicamentoResponseDto registrar(
            @RequestPart("medicamento") String medicamentoJson, // El DTO como String
            @RequestPart("archivo") MultipartFile archivo) throws IOException {

        MedicamentoRequestDto dto = objectMapper.readValue(medicamentoJson, MedicamentoRequestDto.class);
        String nombreImagen = uploadService.saveFile(archivo);
        dto.setRutaImagen(nombreImagen);
        return service.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public MedicamentoResponseDto actualizar(
            @PathVariable Long id,
            @RequestPart("medicamento") String medicamentoJson,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo
    ) throws IOException {

        MedicamentoRequestDto dto = objectMapper.readValue(medicamentoJson, MedicamentoRequestDto.class);

        // 👉 SI VIENE NUEVA IMAGEN
        if (archivo != null && !archivo.isEmpty()) {
            String nombreImagen = uploadService.saveFile(archivo);
            dto.setRutaImagen(nombreImagen); // 🔥 CLAVE
        }

        return service.actualizar(id, dto);
    }

}