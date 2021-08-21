package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.GastosCorrientes;

import java.util.List;

public interface GastosCorrienteService extends  BaseService<GastosCorrientes, String>{

    List<GastosCorrientes> buscarGastosCorrientesPorUsuario(String usuario);

}
