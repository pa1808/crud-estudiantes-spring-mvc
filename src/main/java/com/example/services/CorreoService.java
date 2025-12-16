package com.example.services;

import java.util.List;

import com.example.entities.Correo;
import com.example.entities.Estudiante;

public interface CorreoService {

    public Correo saveCorreo(Correo correo);
    public List<Correo> getAllCorreos();
    public boolean existsByEstudiante(Estudiante estudiante);
    public void deleteByEstudiante(Estudiante estudiante);

}
