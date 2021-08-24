package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.EstatusFinanciero;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstatusFinancieroRepository extends MongoRepository<EstatusFinanciero, String> {
    EstatusFinanciero findByUsuario(String usuario);
    void deleteByUsuario(String usuario);
}
