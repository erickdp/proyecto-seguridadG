package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioMedidasFinancieras;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FormularioMedidasFinancierasRepository extends MongoRepository<FormularioMedidasFinancieras, String>{
    
//    List<FormularioMedidasFinancieras> findByUserByMedidasFinancieras(String user);

    List<FormularioMedidasFinancieras> findByUser(String user);

}
