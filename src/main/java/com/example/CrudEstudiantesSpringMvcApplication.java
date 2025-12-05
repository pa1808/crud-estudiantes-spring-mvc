package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Facultad;
import com.example.entities.Telefono;
import com.example.models.Genero;
import com.example.services.CorreoService;
import com.example.services.EstudianteService;
import com.example.services.FacultadService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEstudiantesSpringMvcApplication implements CommandLineRunner{

	private final FacultadService facultadService;
	private final EstudianteService estudianteService;
	private final CorreoService correoService;
	private final TelefonoService telefonoService;


	public static void main(String[] args) {
		SpringApplication.run(CrudEstudiantesSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//creación de facultades
		Facultad facultad1 = Facultad.builder()
		.nombre("Ingeniería")
		.build();

		Facultad facultad2 = Facultad.builder()
		.nombre("Filología")
		.build();

		facultadService.saveFacultad(facultad1);
		facultadService.saveFacultad(facultad2);

		//creación de estudiantes
		Estudiante estudiante1 = Estudiante.builder()
		.nombre("Paula")
		.primerApellido("López")
		.segundoApellido("Alcalá")
		.genero(Genero.MUJER)
		.fechaMatricula(LocalDate.of(2013, Month.AUGUST, 18))
		.facultad(facultad1)
		.build();

		Estudiante estudiante2 = Estudiante.builder()
		.nombre("Laura")
		.primerApellido("Garcia")
		.segundoApellido("Perez")
		.genero(Genero.MUJER)
		.fechaMatricula(LocalDate.of(1998, Month.APRIL, 1))
		.facultad(facultad1)
		.build();

		Estudiante estudiante3 = Estudiante.builder()
		.nombre("Mario")
		.primerApellido("López")
		.segundoApellido("Adelantado")
		.genero(Genero.HOMBRE)
		.fechaMatricula(LocalDate.of(2007, Month.SEPTEMBER, 10))
		.facultad(facultad2)
		.build();

		Estudiante estudiante4 = Estudiante.builder()
		.nombre("Maria")
		.primerApellido("Hernandez")
		.segundoApellido("Ortiz")
		.genero(Genero.OTRO)
		.fechaMatricula(LocalDate.of(2017, Month.SEPTEMBER, 12))
		.facultad(facultad2)
		.build();

		estudianteService.savEstudiante(estudiante1);
		estudianteService.savEstudiante(estudiante2);
		estudianteService.savEstudiante(estudiante3);
		estudianteService.savEstudiante(estudiante4);

		//creacion de telefonos
		Telefono telefono1 = Telefono.builder()
		.numero("637027094")
		.estudiante(estudiante4)
		.build();

		Telefono telefono2 = Telefono.builder()
		.numero("111111111")
		.estudiante(estudiante4)
		.build();

		Telefono telefono3 = Telefono.builder()
		.numero("222222222")
		.estudiante(estudiante1)
		.build();

		Telefono telefono4 = Telefono.builder()
		.numero("333333333")
		.estudiante(estudiante2)
		.build();

		telefonoService.saveTelefono(telefono1);
		telefonoService.saveTelefono(telefono2);
		telefonoService.saveTelefono(telefono3);
		telefonoService.saveTelefono(telefono4);

		//creacion de correos
		Correo correo1 = Correo.builder()
		.direccion("aa@gmail.com")
		.estudiante(estudiante4)
		.build();

		Correo correo2 = Correo.builder()
		.direccion("bb@gmail.com")
		.estudiante(estudiante3)
		.build();

		Correo correo3 = Correo.builder()
		.direccion("cc@gmail.com")
		.estudiante(estudiante3)
		.build();

		Correo correo4 = Correo.builder()
		.direccion("dd@gmail.com")
		.estudiante(estudiante2)
		.build();

		Correo correo5 = Correo.builder()
		.direccion("ee@gmail.com")
		.estudiante(estudiante1)
		.build();

		correoService.saveCorreo(correo1);
		correoService.saveCorreo(correo2);
		correoService.saveCorreo(correo3);
		correoService.saveCorreo(correo4);
		correoService.saveCorreo(correo5);

	}



}
