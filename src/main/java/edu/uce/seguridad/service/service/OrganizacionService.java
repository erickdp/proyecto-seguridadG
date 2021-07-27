package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.MiClaseException;
import edu.uce.seguridad.model.Organizacion;

public interface OrganizacionService extends BaseService<Organizacion, String> {
    String eliminarPorNombreOrganizacion(String nombreOrganizacion) throws MiClaseException;

    Organizacion buscarPorNombreOrganizacion(String nombreOrganizacion);

    Organizacion actualizarOrganizacion(Organizacion organizacion) throws MiClaseException;
}
