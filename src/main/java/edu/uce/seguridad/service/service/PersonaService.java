package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.Persona;

public interface PersonaService extends BaseService<Persona, String> {
    Persona buscarPersonaPorUsuario(String nombreUsuario, String contrasena);
}
