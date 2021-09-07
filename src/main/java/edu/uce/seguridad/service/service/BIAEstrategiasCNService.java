package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAEstrategiasCN;

import java.util.List;

public interface BIAEstrategiasCNService extends BaseService<BIAEstrategiasCN, String> {
    List<BIAEstrategiasCN> buscarEstrategiasPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;
}
