package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends MongoRepository<Persona, String> {
    @Query("{'usuario.nombreUsuario': ?0, 'usuario.contrasena': ?1}")
    Optional<Persona> findPersonaByUsuarioYContrasena(String nombreUsuario, String contrasena);

    List<Persona> findPersonaByOrganizacion(String organizacion);

    @Query("{'usuario.role': ?0}")
    List<Persona> findPersonaByRole(String role);

    @Query("{'usuario.role': ?0, 'organizacion': ?1}")
    List<Persona> findPersonaByRolAndOrganization(
            String role, String organization
    );

    @Query("{'usuario.nombreUsuario': ?0}")
    Optional<Persona> findPersonaByUsuario(String nombreUsuario);

    List<Persona> findPersonaByOrganizacionAndDepartamento(
            String organizacion, String departamento
    );
}
