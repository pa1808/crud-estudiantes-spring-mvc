package com.example.services;

import java.util.List;

import com.example.entities.Estudiante;
import com.example.entities.Facultad;

public interface FacultadService {
   
   public Facultad saveFacultad(Facultad facultad);
   public List<Facultad> getAllFacultades();
   public Facultad getFacultadById(int facultadId);
   
}
