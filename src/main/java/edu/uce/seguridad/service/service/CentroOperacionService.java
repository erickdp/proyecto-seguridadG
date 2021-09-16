package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.CentroOperacion;

public interface CentroOperacionService extends BaseService<CentroOperacion, String>{
    CentroOperacion buscarCentroPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;

    void eliminarPorUsuario(String usuario);
}
