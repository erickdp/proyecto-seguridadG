package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.Bia2;

import java.util.List;

public interface Bia2Service  extends BaseService<Bia2, String>{
    List<Bia2> buscarporUsuario(String usuario);

    void eliminarporUsuarioBIA(String usuario);
}
