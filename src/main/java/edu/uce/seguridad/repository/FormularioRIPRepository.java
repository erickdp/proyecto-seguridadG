package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.FormularioRIP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FormularioRIPRepository extends MongoRepository<FormularioRIP, String> {

    List<FormularioRIP> findByUserOrderByPrioridadAsc(String user);

    List<FormularioRIP> findByUser(String user);

    void deleteByUser(String user);
}
