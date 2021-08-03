package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.Organizacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrganizacionRepository extends MongoRepository<Organizacion, String> {
    Optional<Organizacion> findOrganizacionByOrganizacion(String nombreOrganizacion);
}
