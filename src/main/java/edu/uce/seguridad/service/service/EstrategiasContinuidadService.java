package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EstrategiasContinuidad;

import java.util.List;

public interface EstrategiasContinuidadService extends BaseService<EstrategiasContinuidad,String> {
    List<EstrategiasContinuidad> buscarPorUsuario(String usuario) throws NoEncontradoExcepcion;
    EstrategiasContinuidad buscarPorUsuarioYEstrategia(String usuario, String estrategia) throws NoEncontradoExcepcion;
    void eliminarConUsuario(String usuario);
}
