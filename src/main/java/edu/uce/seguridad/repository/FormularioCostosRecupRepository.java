package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioCostosRecup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormularioCostosRecupRepository extends MongoRepository<FormularioCostosRecup, String> {

    FormularioCostosRecup findByUsuario(String usuario);

    void deleteByUsuario(String usuario);

}
