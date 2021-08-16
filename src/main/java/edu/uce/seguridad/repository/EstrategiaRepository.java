package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ResumenDeEstrategias;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EstrategiaRepository extends MongoRepository<ResumenDeEstrategias,String> {

    List<ResumenDeEstrategias> findByUsuario(String usuario);
}
