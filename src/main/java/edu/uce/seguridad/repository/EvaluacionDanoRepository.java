package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioEvaluacionDanosII;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EvaluacionDanoRepository extends MongoRepository<FormularioEvaluacionDanosII, String> {

    List<FormularioEvaluacionDanosII> findByUsuario(String usuario);

}
