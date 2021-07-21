package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioAlcance;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormularioAlcanceRepository extends MongoRepository<FormularioAlcance, String> {
    FormularioAlcance findBy_id(ObjectId id);
}
