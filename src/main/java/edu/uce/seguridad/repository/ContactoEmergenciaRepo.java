package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ContactoEmergencia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ContactoEmergenciaRepo extends MongoRepository<ContactoEmergencia, String> {
    @Query("{'departamento': ?0}")
    ContactoEmergencia findContactoByDepartamento(String nombreDepartamento);

    @Query("{'usuario': ?0}")
    List<ContactoEmergencia> findContactosByUsuario(String nombreUsuario);
}
