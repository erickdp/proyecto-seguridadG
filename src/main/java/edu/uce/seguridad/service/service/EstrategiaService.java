package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.ResumenDeEstrategias;

import java.util.Optional;

public interface EstrategiaService extends BaseService<ResumenDeEstrategias,String>{
    Optional<ResumenDeEstrategias> buscarporUsuario(String usuario);
    void eliminarConUsuario(String usuario);
}
