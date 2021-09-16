package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.EstimacionDano;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EstimacionDanoRepository extends MongoRepository<EstimacionDano, String> {
    List<EstimacionDano> findByUsuario(String usuario);

    Optional<EstimacionDano> findByUsuarioAndRiesgo(String usuario, String riesgo);


}
