package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BIAAnalisiImpactoNegocio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BIAAnalisiImpactoNegocioRepository extends MongoRepository<BIAAnalisiImpactoNegocio, String> {
    Optional<BIAAnalisiImpactoNegocio> findByUsuario(String usuario);
}
