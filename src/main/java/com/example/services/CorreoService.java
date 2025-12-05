package com.example.services;

import java.util.List;

import com.example.entities.Correo;

public interface CorreoService {

    public Correo saveCorreo(Correo correo);
    public List<Correo> getAllCorreos();

}
