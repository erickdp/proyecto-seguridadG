package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.repository.PersonaRepository;
import edu.uce.seguridad.service.service.FormularioAlcanceService;
import edu.uce.seguridad.service.service.FormularioLiderazgoService;
import edu.uce.seguridad.service.service.PersonaService;
import edu.uce.seguridad.util.Utileria;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonaServiceImp implements PersonaService {

    private PersonaRepository personaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarTodos() throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findAll();
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros.");
        }
        return personas;
    }

    @Override
    @Transactional
    public Persona agregar(Persona pojo) throws DataAccessException {
        pojo.setUsuario(Utileria.generarUsuario
                (pojo.getNombre(), pojo.getApellido(), pojo.getUsuario().getRole()));
        return this.personaRepository.insert(pojo);
    }

    @Override
    @Transactional
    public Persona actualizar(Persona pojo) throws DataAccessException {
        pojo.setUsuario(Utileria.generarUsuario
                (pojo.getNombre(), pojo.getApellido(), pojo.getUsuario().getRole()));
        return this.personaRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Persona persona = this.personaRepository.findById(identificador).orElse(null);
        if (persona == null) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return persona;
    }

    //    Se lo reemplaza por eliminarPersonaPorNombreUsuario
    @Override
    @Deprecated
    public void eliminarDocumento(String nombreUsuario) throws EliminacionException {

    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPersonaPorUsuarioYContrasena(String nombreUsuario, String contrasena) throws NoEncontradoExcepcion {
        Persona persona = this.personaRepository.findPersonaByUsuarioYContrasena(nombreUsuario, contrasena);
        if (persona == null) {
            throw new NoEncontradoExcepcion("respuesta", "Error en las credenciales ingresadas, pruebe de nuevo.");
        }
        return persona;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorOrganizacion(String organizacion) throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findPersonaByOrganizacion(organizacion);
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(organizacion));
        }
        return personas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorRol(String role) throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findPersonaByRole(role);
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(role));
        }
        return personas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorRoleYOrganizacion(String role, String organizacion) throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findPersonaByRolAndOrganization(role, organizacion);
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(role).concat(" en ").concat(organizacion));
        }
        return personas;
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPersonaPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        Persona persona = this.personaRepository.findPersonaByUsuario(nombreUsuario);
        if (persona == null) {
            throw new NoEncontradoExcepcion("respuesta", "Error en las credenciales ingresadas, no se han encontrado registros.");
        }
        return persona;
    }

    @Override
    @Transactional
    public void eliminarPersonaPorNombreUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        Persona persona = this.buscarPersonaPorUsuario(nombreUsuario);
        if (persona == null) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(nombreUsuario));
        }

        this.personaRepository.deleteById(persona.get_id());
    }

    @Override
    public List<Persona> buscarPersonasPorOrganizacionYDepartamento(String organizacion, String departamento) throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findPersonaByOrganizacionAndDepartamento(
                organizacion, departamento
        );
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(departamento).concat(" en ").concat(organizacion));
        }
        return personas;
    }
}
