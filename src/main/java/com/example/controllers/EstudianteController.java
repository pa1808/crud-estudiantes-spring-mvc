package com.example.controllers;


import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Facultad;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.EstudianteService;
import com.example.services.FacultadService;
import com.example.services.TelefonoService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




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
        
    }

    @GetMapping("/alta")
    public String altaEstudiante(Model model) {

        Estudiante estudiante = new Estudiante();
        model.addAttribute("estudiante", estudiante);

        List<Facultad> facultades = facultadService.getAllFacultades();
        model.addAttribute("facultades", facultades);

        return "formularioAltaModificacion";
    }
    
    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante, 
        @RequestParam(name = "numerosTelefono", required = false) String telefonos,
        @RequestParam(name = "direccionesCorreo", required = false) String correos) {

        estudianteService.savEstudiante(estudiante);

        if(telefonos!=null){

            String[] arrayTelefonos = telefonos.split(";");
            List<String> listadoTelefonos = Arrays.asList(arrayTelefonos);

            listadoTelefonos.forEach(t -> {

                telefonoService.saveTelefono(
                    Telefono.builder()
                    .numero(t.trim())
                    .estudiante(estudiante)
                    .build()
                );
            });
        }

        if(correos!=null){

            String[] arrayCorreos = correos.split(";");
            List<String> listadoCorreos = Arrays.asList(arrayCorreos);

            listadoCorreos.forEach(c -> {
                correoService.saveCorreo(
                    Correo.builder()
                    .direccion(c.trim())
                    .estudiante(estudiante)
                    .build()
                );
            });
        }

        return "redirect:/estudiantes/listar";
    }
    
}
