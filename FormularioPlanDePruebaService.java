package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioPlanDePrueba;

import java.util.List;

public interface FormularioPlanDePruebaService extends BaseService<FormularioPlanDePrueba, String>{
    List<FormularioPlanDePrueba> buscarPorUserFiltrarPorTipoDeEjercicio (String user) throws NoEncontradoExcepcion;

    void eliminarPorTipoDeEjercicio(String user);
}
