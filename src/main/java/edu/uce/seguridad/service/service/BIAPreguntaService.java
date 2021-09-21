package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.BIAPregunta;

public interface BIAPreguntaService extends BaseService<BIAPregunta, String> {
    BIAPregunta actualizarPreguntaPosicion(BIAPregunta pojo, Integer posicion);
    void eliminarPreguntaPosicion(String identificador, Integer posicion);
}
