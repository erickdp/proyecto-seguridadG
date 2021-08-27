package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioPlanDePrueba;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FormularioPlanDePruebaRepository extends MongoRepository<FormularioPlanDePrueba, String> {

    List<FormularioPlanDePrueba> findByUserOrderByTipoDeEjercicio(String user);
}
