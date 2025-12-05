package com.example.services;

import java.util.List;

import com.example.entities.Telefono;

public interface TelefonoService {
    public Telefono saveTelefono(Telefono telefono);
    public List<Telefono> getAllTelefonos();
}
