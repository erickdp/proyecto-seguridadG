package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.Organizacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizacionRepository extends MongoRepository<Organizacion, String> {
    Organizacion findOrganizacionByOrganizacion(String nombreOrganizacion);
}
