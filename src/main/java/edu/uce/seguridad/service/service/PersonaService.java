package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.MiClaseException;
import edu.uce.seguridad.model.Persona;

import java.util.List;

public interface PersonaService extends BaseService<Persona, String> {
    Persona buscarPersonaPorUsuarioYContrasena(String nombreUsuario, String contrasena);

    List<Persona> buscarPersonaPorOrganizacion(String organizacion);

    List<Persona> buscarPersonaPorRol(String role);

    List<Persona> buscarPersonaPorRoleYOrganizacion(
            String role,
            String organizacion
    );

    Persona buscarPersonaPorUsuario(String nombreUsuario);

    String eliminarPersonaPorNombreUsuario(String nombreUsuario) throws MiClaseException;

//    Metodo provisional en caso de que hayan varias personas encargadas de un mismo depar
    List<Persona> buscarPersonasPorOrganizacionYDepartamento(String organizacion,
                                                             String departamento);
}
