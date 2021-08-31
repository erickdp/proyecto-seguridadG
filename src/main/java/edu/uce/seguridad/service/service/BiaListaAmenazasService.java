package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.BiaListaAmenazas;

public interface BiaListaAmenazasService extends BaseService<BiaListaAmenazas, String> {

    BiaListaAmenazas buscarPorUsuario(String usuario);

}
