package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.BiaValoracionGeneral;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BiaValoracionGeneralRepository extends MongoRepository<BiaValoracionGeneral, String> {

    BiaValoracionGeneral findByUsuario(String usuario);
    void deleteByUsuario(String usuario);
}
