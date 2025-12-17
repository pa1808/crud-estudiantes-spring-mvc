package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Estudiante;
import com.example.entities.Telefono;
import java.util.List;


@Repository
public interface TelefonoDao extends JpaRepository<Telefono, Integer>{

    boolean existsByEstudiante(Estudiante estudiante);
    void deleteByEstudiante(Estudiante estudiante);

}
