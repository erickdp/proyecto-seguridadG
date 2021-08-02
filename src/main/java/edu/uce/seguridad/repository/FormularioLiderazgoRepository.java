package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioLiderazgo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormularioLiderazgoRepository extends MongoRepository<FormularioLiderazgo, String> {
   // FormularioLiderazgo findBy_id(ObjectId id);
    FormularioLiderazgo findFormularioLiderazgoByUser(String user);
   // FormularioLiderazgo findFormularioLiderazgoBy_departamenti(String departamento);

}
