package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.TelefonoDao;
import com.example.entities.Estudiante;
import com.example.entities.Telefono;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelefonoServiceImpl implements TelefonoService{
    
    private final TelefonoDao telefonoDao;

    @Override
    public Telefono saveTelefono(Telefono telefono) {
        return telefonoDao.save(telefono);
    }

    @Override
    public List<Telefono> getAllTelefonos() {
        return telefonoDao.findAll();
    }

    @Override
    public boolean existsByEstudiante(Estudiante estudiante) {
        return telefonoDao.existsByEstudiante(estudiante);
    }

    @Override
    public void deleteByEstudiante(Estudiante estudiante) {
        telefonoDao.deleteByEstudiante(estudiante);
    }


}
