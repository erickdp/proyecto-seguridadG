package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ContactoEmergencia;

public interface ContactoEmergenciaService extends BaseService<ContactoEmergencia, String>{
    ContactoEmergencia buscarContactoPorDepartamento(String nombreDepartamento) throws NoEncontradoExcepcion;
}
