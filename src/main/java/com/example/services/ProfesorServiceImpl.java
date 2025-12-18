package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.ProfesorDao;
import com.example.entities.Profesor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfesorServiceImpl  implements ProfesorService{

    private final ProfesorDao profesorDao;

    @Override
    public Profesor saveProfesor(Profesor profesor) {
        return profesorDao.save(profesor);
    }

    @Override
    public List<Profesor> findAllProfesores() {
        return profesorDao.findAll();
    }

    @Override
    public Profesor findProfesor(int idProfesor) {
        return profesorDao.findById(idProfesor).get();
    }

    @Override
    public void deleteProfesor(Profesor profesor) {
        profesorDao.delete(profesor);
    }


    @Override
    public Profesor updateProfesor(int idProfesor) {
      return  profesorDao.save(profesorDao.findById(idProfesor).get());
    }



}
