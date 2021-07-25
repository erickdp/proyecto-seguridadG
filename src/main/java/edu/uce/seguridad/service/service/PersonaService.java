package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.Persona;

import java.util.List;

public interface PersonaService extends BaseService<Persona, String> {
    Persona buscarPersonaPorUsuario(String nombreUsuario, String contrasena);

    List<Persona> buscarPersonaPorOrganizacion(String organizacion);
}
