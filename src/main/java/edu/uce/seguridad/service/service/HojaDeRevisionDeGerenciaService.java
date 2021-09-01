package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.HojaDeRevisionDeGerencia;
import java.util.List;

public interface HojaDeRevisionDeGerenciaService extends BaseService<HojaDeRevisionDeGerencia, String> {
    List<HojaDeRevisionDeGerencia> buscarPorUserFiltrarPorAsuntoARevisaryVerificar (String user) throws NoEncontradoExcepcion;

    void eliminarPorAsuntoARevisaryVerificar(String user);
}
