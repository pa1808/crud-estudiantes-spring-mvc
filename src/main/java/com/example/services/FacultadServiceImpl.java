package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.FacultadDao;
import com.example.entities.Estudiante;
import com.example.entities.Facultad;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacultadServiceImpl implements FacultadService{
    
    private final FacultadDao facultadDao;

    @Override
    public Facultad saveFacultad(Facultad facultad) {
        return facultadDao.save(facultad);
    }

    @Override
    public List<Facultad> getAllFacultades() {
        return facultadDao.findAll();
    }

    @Override
    public Facultad getFacultadById(int facultadId) {
        return facultadDao.findById(facultadId).get();
    }

    
}
