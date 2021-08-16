package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ContactoEmergencia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ContactoEmergenciaRepo extends MongoRepository<ContactoEmergencia, String> {
    @Query("{'departamento': ?0}")
    ContactoEmergencia findContactoByDepartamento(String nombreDepartamento);
}
