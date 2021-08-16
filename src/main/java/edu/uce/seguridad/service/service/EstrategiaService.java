package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.ResumenDeEstrategias;

import java.util.List;

public interface EstrategiaService extends BaseService<ResumenDeEstrategias,String>{
    List<ResumenDeEstrategias> buscarporUsuario(String usuario);
}
