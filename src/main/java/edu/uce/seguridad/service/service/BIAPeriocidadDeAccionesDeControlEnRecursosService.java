package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAPeriocidadDeAccionesDeControlEnRecursos;
import java.util.List;




public interface BIAPeriocidadDeAccionesDeControlEnRecursosService extends BaseService<BIAPeriocidadDeAccionesDeControlEnRecursos, String> {
     List<BIAPeriocidadDeAccionesDeControlEnRecursos> buscarPorUserFiltrarPorInmueble (String user) throws NoEncontradoExcepcion;
    
    void eliminarPorInmueble (String user);  
     void eliminarConUsuario(String user);
    
}
