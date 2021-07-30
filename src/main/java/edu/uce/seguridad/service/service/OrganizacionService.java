package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Organizacion;

public interface OrganizacionService extends BaseService<Organizacion, String> {
    void eliminarPorNombreOrganizacion(String nombreOrganizacion) throws NoEncontradoExcepcion;

    Organizacion buscarPorNombreOrganizacion(String nombreOrganizacion) throws NoEncontradoExcepcion;
}
