package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAControlesAlineadosALosImpactos;
import java.util.List;


public interface BIAControlesAlineadosALosImpactosService extends BaseService<BIAControlesAlineadosALosImpactos, String>{
    List<BIAControlesAlineadosALosImpactos> buscarPorUserFiltrarPorInmueble (String user) throws NoEncontradoExcepcion;
    
    void eliminarBIAControlesAlineadosALosImpactos (String user);
}
