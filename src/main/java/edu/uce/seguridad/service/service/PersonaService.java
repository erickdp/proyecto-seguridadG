package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Persona;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.List;

public interface PersonaService extends BaseService<Persona, String> {
    Persona buscarPersonaPorUsuarioYContrasena(String nombreUsuario, String contrasena) throws NoEncontradoExcepcion;

    List<Persona> buscarPersonaPorOrganizacion(String organizacion) throws NoEncontradoExcepcion;

    List<Persona> buscarPersonaPorRol(String role) throws NoEncontradoExcepcion;

    List<Persona> buscarPersonaPorRoleYOrganizacion(
            String role,
            String organizacion
    ) throws NoEncontradoExcepcion;

    Persona buscarPersonaPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;

    void eliminarPersonaPorNombreUsuario(String nombreUsuario) throws NoEncontradoExcepcion;

    //    Metodo provisional en caso de que hayan varias personas encargadas de un mismo depar
    List<Persona> buscarPersonasPorOrganizacionYDepartamento(String organizacion,
                                                             String departamento) throws NoEncontradoExcepcion;

    byte[] generarPdfEnBytes(String organizacion) throws IOException, JRException;
}
