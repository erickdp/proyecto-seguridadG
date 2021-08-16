package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ListaContactoExterno;
import java.util.List;

public interface ListaContactoExternoService extends BaseService<ListaContactoExterno, String> {

    List<ListaContactoExterno> buscarPorUserFiltrarPorTipoContacto(String user) throws NoEncontradoExcepcion;

    void eliminarConcatosPorUser(String user);
}
