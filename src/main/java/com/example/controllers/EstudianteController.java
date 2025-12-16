package com.example.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




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
        @RequestParam(name = "direccionesCorreo", required = false) String correos,
        @RequestParam(name = "imagen", required = false) MultipartFile imagen) {


        if(imagen != null && !imagen.isEmpty()){
            Path rutaRelativa = Paths.get("src\\main\\resources\\static\\imagenes");
            String rutaAbsoluta = rutaRelativa.toFile().getAbsolutePath();
            Path rutaCompleta = Paths.get(rutaAbsoluta+"\\"+imagen.getOriginalFilename());

            
            try {
                byte[] imagenRecibidaBytes;
                imagenRecibidaBytes = imagen.getBytes();
                Files.write(rutaCompleta, imagenRecibidaBytes);
                estudiante.setFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.getMessage();
            }
            
            
        }

        estudianteService.savEstudiante(estudiante);

        if(telefonos!=null){

            String[] arrayTelefonos = telefonos.split(";");
            List<String> listadoTelefonos = Arrays.asList(arrayTelefonos);

           if(telefonoService.existsByEstudiante(estudiante)){
            telefonoService.deleteByEstudiante(estudiante);
           }

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

            if(correoService.existsByEstudiante(estudiante)){
                correoService.deleteByEstudiante(estudiante);
            }

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

    @GetMapping("/delete/{id}")
    public String deleteEstudiante(@PathVariable(name = "id") int idEstudiante) {

       Estudiante estudiante = estudianteService.getEstudiante(idEstudiante);
       estudianteService.deleteEstudiante(estudiante);

       
        //comprobar si el empleado tiene foto
        if(estudiante.getFoto() != null){

            //ruta relativa a la imagen a elimianr
            Path rutaRelativa = Paths.get("src\\main\\resources\\static\\imagenes\\"+estudiante.getFoto());

            //comprobar si la ruta relativa existe
            if(Files.exists(rutaRelativa)){
                try {
                    Files.delete(rutaRelativa);
                } catch (IOException e) {
                   
                    e.printStackTrace();
                }
            }

        }


        return "redirect:/estudiantes/listar";
    }

    @GetMapping("/update/{id}")
    public String updateEstudiante(Model model, @PathVariable(name = "id") int idEstudiante) {

        Estudiante estudiante = estudianteService.getEstudiante(idEstudiante);
        model.addAttribute("estudiante",estudiante);
        model.addAttribute("facultades",facultadService.getAllFacultades());

        List<Telefono> telefonosLista = telefonoService.getAllTelefonos();
        List<Telefono> telefonosEmpleado = telefonosLista.stream()
        .filter(t -> t.getEstudiante().equals(estudiante))
        .toList();
        if(telefonosEmpleado != null){
            String telefonos = telefonosEmpleado.stream()
            .map(t -> t.getNumero())
            .collect(Collectors.joining(";"));
            model.addAttribute("numerosTelefono",telefonos);
        }

        List<Correo> correosLista = correoService.getAllCorreos();
        List<Correo> correosEmpleado = correosLista.stream()
        .filter(c -> c.getEstudiante().equals(estudiante))
        .toList();

        if(correosEmpleado != null){
            String correos = correosEmpleado.stream()
            .map(c -> c.getDireccion())
            .collect(Collectors.joining(";"));
            model.addAttribute("direccionesCorreo",correos);
        }
        

        return "formularioAltaModificacion";
    }
    
    @GetMapping("/detalles/{id}")
    public String detallesEstudiante(Model model, @PathVariable(name = "id") int idEstudiante) {

        Estudiante estudiante = estudianteService.getEstudiante(idEstudiante);
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("telefonos", estudiante.getTelefonos());
        model.addAttribute("correos", estudiante.getCorreos());

        return "detallesEstudiante";
    }
    
    
    
}
