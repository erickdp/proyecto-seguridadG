package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioMedidasFinancieras;

import java.util.List;

public interface FormularioMedidasFinancierasService extends BaseService<FormularioMedidasFinancieras, String>{
    
    List<FormularioMedidasFinancieras> buscarPorUserFiltrarPorMedidasFinancieras (String user) throws NoEncontradoExcepcion;

    void eliminarPorMedidasFinancieras (String user);
    void eliminarPorUsuarioFinanciero(String nombreUsuario);

}
