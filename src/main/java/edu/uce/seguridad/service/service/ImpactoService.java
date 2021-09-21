package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ImpactoNegocio;

public interface ImpactoService extends BaseService<ImpactoNegocio, String> {
    ImpactoNegocio buscarImpactoPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;

    void eliminarImpactosUsuario(String usuario);
}
