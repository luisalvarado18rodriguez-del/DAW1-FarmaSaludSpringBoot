package com.cibertec.FarmaSalud.business.domain.service.impl;

import com.cibertec.FarmaSalud.business.domain.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String root = "uploads"; // Carpeta principal en la raíz del proyecto

    @Override
    public String guardarArchivo(MultipartFile archivo, String carpeta) {
        try {
            // 1. Crear ruta: uploads/recetas
            Path directorioDestino = Paths.get(root, carpeta);
            if (!Files.exists(directorioDestino)) {
                Files.createDirectories(directorioDestino);
            }

            // 2. Nombre único para evitar que archivos con el mismo nombre se sobreescriban
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();

            // 3. Guardar el archivo
            Files.copy(archivo.getInputStream(), directorioDestino.resolve(nombreArchivo));

            return nombreArchivo; // Devolvemos el nombre para guardarlo en la DB
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar el archivo: " + e.getMessage());
        }
    }
}