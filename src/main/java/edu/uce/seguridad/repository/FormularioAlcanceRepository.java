package edu.uce.seguridad.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//No usar aun
public interface FormularioAlcanceRepository extends MongoRepository<FormularioAlcance, String> {
    FormularioAlcance findBy_id(ObjectId id);

    FormularioAlcance findByPregunta(String pregunta);
}
