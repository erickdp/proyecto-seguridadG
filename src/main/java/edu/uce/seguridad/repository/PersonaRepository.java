package edu.uce.seguridad.repository;

import edu.uce.seguridad.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonaRepository extends MongoRepository<Persona, String> {
    @Query("{'usuario.nombreUsuario': ?0, 'usuario.contrasena': ?1}")
    Persona findPersonaByUsuarioYContrasena(String nombreUsuario, String contrasena);
//    Persona findByUsuarioNombreUsuarioAndUsuarioContrasena(String nombreUsuario, String contrasena);

    List<Persona> findPersonaByOrganizacion(String organizacion);

    @Query("{'usuario.role': ?0}")
    List<Persona> findPersonaByRole(String role);
//    List<Persona> findByUsuarioRole(String role);

    @Query("{'usuario.role': ?0, 'organizacion': ?1}")
    List<Persona> findPersonaByRolAndOrganization(
            String role, String organization
    );
//    List<Persona> findByUsuarioRoleAndOrganizacion(String role, String organizacion);

    @Query("{'usuario.nombreUsuario': ?0}")
    Persona findPersonaByUsuario(String nombreUsuario);
//    Persona findByUsuarioNombreUsuario(String nombreUsuario);

}
