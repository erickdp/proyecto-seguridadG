package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BIAEstrategiasCN;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BIAEstrategiasCNRepo extends MongoRepository<BIAEstrategiasCN, String> {
    List<BIAEstrategiasCN> findEstrategiasByUsuario(String nombreUsuario);
}
