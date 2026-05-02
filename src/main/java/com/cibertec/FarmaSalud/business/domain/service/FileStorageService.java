package com.cibertec.FarmaSalud.business.domain.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String guardarArchivo(MultipartFile archivo, String carpeta);
}