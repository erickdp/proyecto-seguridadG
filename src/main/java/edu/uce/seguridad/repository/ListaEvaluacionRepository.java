package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ListaEvaluacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ListaEvaluacionRepository extends MongoRepository<ListaEvaluacion, String> {

    List<ListaEvaluacion> findByUserOrderByTipoCalidadAsc (String user);

}
