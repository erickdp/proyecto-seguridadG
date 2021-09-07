package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAAutomatizacionDeControlEnRecursos;
import java.util.List;


public interface BIAAutomatizacionDeControlEnRecursosService extends BaseService<BIAAutomatizacionDeControlEnRecursos, String> {
        List<BIAAutomatizacionDeControlEnRecursos> buscarPorUserFiltrarPorInmueble (String user) throws NoEncontradoExcepcion;
    
    void eliminarPorInmueble (String user);   
     
}
