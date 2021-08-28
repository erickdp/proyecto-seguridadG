package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.Bia2;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Bia2Repository extends MongoRepository<Bia2, String> {
    List<Bia2> findBia2ByUsuario(String usuario);
}
