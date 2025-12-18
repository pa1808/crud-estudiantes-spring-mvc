package com.example.services;

import java.util.List;

import com.example.entities.Profesor;

public interface ProfesorService {
    public Profesor saveProfesor(Profesor profesor);
    public List<Profesor> findAllProfesores();
    public Profesor findProfesor(int idProfesor);
    public void deleteProfesor(Profesor profesor);
    public Profesor updateProfesor(int idProfesor);


}
