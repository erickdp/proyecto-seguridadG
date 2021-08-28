package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.BIACuestionario;

import java.util.Map;

public interface BIACuestionarioService extends BaseService<BIACuestionario, String> {
    Map<String, Double> obtenerPromedioCuestionario(String organizacion);
}
