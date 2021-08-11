package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.PeriodoTolerable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PeriodoTolerableRepository extends MongoRepository<PeriodoTolerable, String> {
    @Query("{'usuario.nombreUsuario': ?0}")
    List<PeriodoTolerable> findPeriodosByUsuario(String nombreUsuario);
}
