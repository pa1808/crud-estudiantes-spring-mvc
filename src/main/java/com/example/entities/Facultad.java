package com.example.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "facultades")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class Facultad implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facultad", cascade = CascadeType.REMOVE)
    private List<Estudiante> estudiantes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facultad", cascade = CascadeType.REMOVE)
    private List<Profesor> profesores;

}
