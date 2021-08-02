package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.FormularioLiderazgo;

public interface FormularioLiderazgoService extends  BaseService<FormularioLiderazgo, String> {
    FormularioLiderazgo buscarFormularioPorUsuario(String usuario);

    // Metodo para eliminar la respuesta al formulario Liderazgo perteneciente a un user, cuando se elimina al user
    void eliminarRespuestaFormularioLiderazgo(String nombreUsuario);

}
