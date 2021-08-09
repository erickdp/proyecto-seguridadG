package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.EstimacionDano;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EstimacionDanoRepository extends MongoRepository<EstimacionDano, String> {
    Optional<EstimacionDano> findByUsuario(String usuario);
}
