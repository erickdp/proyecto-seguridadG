package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.RevisionContinua;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RevisionContinuaRepo extends MongoRepository<RevisionContinua, String> {
    @Query("{'usuario': ?0}")
    RevisionContinua findRevisionByUsuario(String nombreUsuario);
}
