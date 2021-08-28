package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BIACuestionario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BIACuestionarioRepository extends MongoRepository<BIACuestionario, String> {
    Optional<BIACuestionario> findByUsuario(String usuario);
}
