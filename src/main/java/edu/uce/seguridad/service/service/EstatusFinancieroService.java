package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.EstatusFinanciero;

public interface EstatusFinancieroService extends BaseService<EstatusFinanciero, String> {
    EstatusFinanciero buscarPorUsuario(String usuario);
    void eliminarPorUsuario(String usuario);
}
