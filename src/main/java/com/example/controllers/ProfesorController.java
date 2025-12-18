package com.example.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Facultad;
import com.example.entities.Profesor;
import com.example.services.FacultadService;
import com.example.services.ProfesorService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;
    private final FacultadService facultadService;

    @GetMapping("/listar")
    public String listarProfesores(Model model) {

        model.addAttribute("profesores", profesorService.findAllProfesores());

        return "listadoProfesores";
    }

    @GetMapping("/alta")
    public String altaProfesor(Model model) {

        Profesor profesor = new Profesor();
        model.addAttribute("profesor", profesor);

        List<Facultad> facultades = facultadService.getAllFacultades();
        model.addAttribute("facultades", facultades);

        return "formularioAltaModificacionProfesor";
    }

    @PostMapping("/guardar")
    public String guardarProfesor(@ModelAttribute Profesor profesor,
        @RequestParam(name = "imagen", required = false) MultipartFile imagen
    ) {
        if(imagen != null && !imagen.isEmpty()){
            Path rutaRelativa = Paths.get("src\\main\\resources\\static\\imagenes");
            String rutaAbsoluta = rutaRelativa.toFile().getAbsolutePath();
            Path rutaCompleta = Paths.get(rutaAbsoluta+"\\"+imagen.getOriginalFilename());

            
            try {
                byte[] imagenRecibidaBytes;
                imagenRecibidaBytes = imagen.getBytes();
                Files.write(rutaCompleta, imagenRecibidaBytes);
                profesor.setFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.getMessage();
            }
            
            
        }

        profesorService.saveProfesor(profesor);
        
        return "redirect:/profesores/listar";
    }

    @GetMapping("/delete/{id}")
    public String deleteProfesor(@PathVariable(name = "id") int idProfesor, Model model) {

        Profesor profesor = profesorService.findProfesor(idProfesor);

        
        if(profesor.getFoto() != null){

            //ruta relativa a la imagen a elimianr
            Path rutaRelativa = Paths.get("src\\main\\resources\\static\\imagenes\\"+profesor.getFoto());

            //comprobar si la ruta relativa existe
            if(Files.exists(rutaRelativa)){
                try {
                    Files.delete(rutaRelativa);
                } catch (IOException e) {
                   
                    e.printStackTrace();
                }
            }

        }

        profesorService.deleteProfesor(profesor);

        return "redirect:/profesores/listar";
    }


    @GetMapping("/update/{id}")
    public String updateProfesor(Model model, @PathVariable(name = "id") int idProfesor) {

        Profesor profesor = profesorService.findProfesor(idProfesor);

        model.addAttribute("profesor", profesor);

        model.addAttribute("facultades",facultadService.getAllFacultades());

        return "formularioAltaModificacionProfesor";
    }

    @GetMapping("/detalles/{id}")
    public String detallesProfesor(Model model, @PathVariable(name = "id") int idProfesor) {

        model.addAttribute("profesor", profesorService.findProfesor(idProfesor));
        return "detallesProfesor";
    }
    
    
    
    
    
    
}
