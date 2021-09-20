package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAEficenciaBasadaEnControlesDeRecursos;
import java.util.List;

public interface BIAEficenciaBasadaEnControlesDeRecursosService extends BaseService<BIAEficenciaBasadaEnControlesDeRecursos, String> {

    List<BIAEficenciaBasadaEnControlesDeRecursos> buscarPorUserFiltrarPorInmueble(String user) throws NoEncontradoExcepcion;

    void eliminarBIAEficenciaBasadaEnControlesDeRecursos(String user);
}
