package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.EstudianteDao;
import com.example.entities.Estudiante;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService{
    
    private final EstudianteDao estudianteDao;

    @Override
    public Estudiante savEstudiante(Estudiante e) {
        return estudianteDao.save(e);
    }

  
    @Override
    public Estudiante getEstudiante(int estudianteId) {
        return estudianteDao.findById(estudianteId).get();
    }

    @Override
    public void deleteEstudiante(Estudiante e) {
        estudianteDao.delete(e);
    }

    @Override
    public Estudiante updateEstudiante(int estudianteId) {
        return estudianteDao.save(estudianteDao.findById(estudianteId).get());
    }

    @Override
    public List<Estudiante> getEstudianteNombre(String nombre) {
        return estudianteDao.findByNombre(nombre);
    }


    @Override
    public List<Estudiante> getAllEstudiantes() {
        return estudianteDao.findAll();
    }

}
