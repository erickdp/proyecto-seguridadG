package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.ActividadesPrioritarias;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ActividadesPrioritariasRepository extends MongoRepository<ActividadesPrioritarias, String> {
    @Query("{'usuario.nombreUsuario': ?0}")
    List<ActividadesPrioritarias> findActividadesByUsuario(String nombreUsuario);
}
