package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.FormularioAlcance;

public interface FormularioAlcanceService extends BaseService<FormularioAlcance, String> {

    FormularioAlcance buscarFormularioAlPorUsua(String usuario);
}
