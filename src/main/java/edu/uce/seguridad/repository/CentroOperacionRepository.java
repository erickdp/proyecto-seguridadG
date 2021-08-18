package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.CentroOperacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CentroOperacionRepository extends MongoRepository<CentroOperacion, String> {
    @Query("{'usuario': ?0}")
    CentroOperacion findCentroByUsuario(String nombreUsuario);
}
