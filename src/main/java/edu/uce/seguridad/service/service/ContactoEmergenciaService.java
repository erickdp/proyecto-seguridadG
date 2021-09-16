package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ContactoEmergencia;

import java.util.List;

public interface ContactoEmergenciaService extends BaseService<ContactoEmergencia, String>{
    ContactoEmergencia buscarContactoPorDepartamento(String nombreDepartamento) throws NoEncontradoExcepcion;

    List<ContactoEmergencia> buscarContactosPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;

    void eliminarConUsuario(String usuario);
}
