package com.example.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.models.Genero;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "estudiantes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaMatricula;

    @ManyToOne(fetch = FetchType.LAZY)
    private Facultad facultad;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante", cascade = CascadeType.REMOVE)
    private List<Telefono> telefonos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante", cascade = CascadeType.REMOVE)
    private List<Correo> correos;

}
