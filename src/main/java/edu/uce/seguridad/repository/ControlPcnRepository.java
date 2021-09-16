package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ControlPcn;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ControlPcnRepository extends MongoRepository<ControlPcn, String> {
    Optional<ControlPcn> findByUsuario(String usuario);
}
