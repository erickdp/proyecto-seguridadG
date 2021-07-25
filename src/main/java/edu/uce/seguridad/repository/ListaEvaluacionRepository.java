package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ListaEvaluacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListaEvaluacionRepository extends MongoRepository<ListaEvaluacion, String> {

}
