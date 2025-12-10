package com.example.controllers;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Estudiante;
import com.example.services.CorreoService;
import com.example.services.EstudianteService;
import com.example.services.FacultadService;
import com.example.services.TelefonoService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final FacultadService facultadService;
    private final CorreoService correoService;
    private final TelefonoService telefonoService;

    @GetMapping("/listar")
     public String listarEmpleados(Model model){

        List<Estudiante> estudiantes = estudianteService.getAllEstudiantes();
        model.addAttribute("estudiantes",estudiantes);

        return "listadoEstudiantes";
        
    };

}
