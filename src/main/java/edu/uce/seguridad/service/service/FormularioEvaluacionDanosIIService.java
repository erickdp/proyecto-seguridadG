package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.FormularioEvaluacionDanosII;

import java.util.List;

public interface FormularioEvaluacionDanosIIService extends BaseService<FormularioEvaluacionDanosII, String> {

    List<FormularioEvaluacionDanosII> buscarPorUsuario(String usuario);

    void eliminarEvaluacionesPorUser(String user);

}
