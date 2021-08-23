package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.FormularioCostosRecup;

public interface FormularioCostosRecupService extends BaseService<FormularioCostosRecup, String> {

    FormularioCostosRecup buscarPorUsuario(String usuario);

    void eliminarPorUsuario(String usuario);

}
