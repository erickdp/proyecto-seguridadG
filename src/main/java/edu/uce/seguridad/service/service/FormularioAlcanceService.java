package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.FormularioAlcance;

public interface FormularioAlcanceService extends BaseService<FormularioAlcance, String> {

    FormularioAlcance buscarFormularioAlPorUsua(String usuario);

    // Metodo para eliminar la respuesta al formulario Liderazgo perteneciente a un user, cuando se elimina al user
    void eliminarRespuestaFormularioAlcance(String nombreUsuario);
}
