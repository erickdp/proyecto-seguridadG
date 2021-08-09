package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RecursoRepository extends MongoRepository<Recurso, String> {
    @Query("{'usuario.nombreUsuario': ?0}")
    List<Recurso> findRecursosByUsuario(String nombreUsuario);

    @Query("{'usuario.nombreUsuario': ?0}")
    Recurso findRecursoByUsuario(String nombreUsuario);
}
