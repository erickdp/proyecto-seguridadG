package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ResumenDeEstrategias;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EstrategiaRepository extends MongoRepository<ResumenDeEstrategias,String> {

    Optional<ResumenDeEstrategias> findByUsuario(String usuario);
}
