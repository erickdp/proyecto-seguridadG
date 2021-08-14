package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.FormularioListadeAcopio;
import edu.uce.seguridad.repository.FormularioListadeAcopioRepository;

import java.util.List;

public interface FormularioListadeAcopioService  extends  BaseService<FormularioListadeAcopio, String>{
    List<FormularioListadeAcopio> buscarporUsuario(String usuario);

     void eliminarPorUsuario(String usuario);

}
