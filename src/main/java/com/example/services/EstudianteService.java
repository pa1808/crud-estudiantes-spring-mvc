package com.example.services;

import java.util.List;

import com.example.entities.Estudiante;

public interface EstudianteService {

    public Estudiante savEstudiante(Estudiante e);
    public List<Estudiante> getAllEstudiantes();
    public Estudiante getEstudiante(int estudianteId);
    
}
