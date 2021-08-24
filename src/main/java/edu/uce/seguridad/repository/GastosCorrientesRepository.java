package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.GastosCorrientes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GastosCorrientesRepository extends MongoRepository<GastosCorrientes, String> {

    GastosCorrientes findByUser(String user);

    void deleteByUser(String user);
}
