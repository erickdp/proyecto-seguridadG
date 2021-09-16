package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.BiaValoracionGeneral;

public interface BiaValoracionGeneralService extends BaseService<BiaValoracionGeneral, String> {

    BiaValoracionGeneral buscarPorUsuario(String usuario);

}
