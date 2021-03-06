package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ListaContacto;

import java.util.List;

public interface ListaContactoService extends BaseService<ListaContacto, String> {

    List<ListaContacto> buscarPorUserFiltrarPorTipoContacto (String user) throws NoEncontradoExcepcion;

    void eliminarConcatosPorUser(String user);
}
