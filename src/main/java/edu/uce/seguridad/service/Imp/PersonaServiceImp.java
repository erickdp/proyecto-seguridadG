package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.MiClaseException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.repository.PersonaRepository;
import edu.uce.seguridad.service.service.PersonaService;
import edu.uce.seguridad.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarTodos() {
        return this.personaRepository.findAll();
    }

    @Override
    @Transactional
    public Persona agregar(Persona pojo) {
        pojo.setUsuario(Utileria.generarUsuario
                (pojo.getNombre(), pojo.getApellido(), pojo.getUsuario().getRole()));
        return this.personaRepository.insert(pojo);
    }

    @Override
    @Transactional
    public Persona actualizar(Persona pojo) {
        return this.personaRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscaPorId(String identificador) {
        return this.personaRepository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String nombreUsuario) {
        Persona persona = this.buscarPersonaPorUsuario(nombreUsuario);
        if (persona != null) {
            this.personaRepository.deleteById(persona.get_id());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPersonaPorUsuarioYContrasena(String nombreUsuario, String contrasena) throws MiClaseException {
        Persona persona = this.personaRepository.findPersonaByUsuarioYContrasena(nombreUsuario, contrasena);
        if (persona == null) {
            throw new MiClaseException("respuesta", "Error en las credenciales ingresadas, pruebe de nuevo.");
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
            throw new MiClaseException("respuesta", "Error en las credenciales ingresadas, no se han encontrado registros.");
        }
        return persona;
    }

    @Override
    @Transactional
    public void eliminarPersonaPorNombreUsuario(String nombreUsuario) throws MiClaseException {
        Persona persona = this.buscarPersonaPorUsuario(nombreUsuario);
        this.personaRepository.deleteById(persona.get_id());
    }

    @Override
    public List<Persona> buscarPersonasPorOrganizacionYDepartamento(String organizacion, String departamento) throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findPersonaByOrganizacionAndDepartamento(
                organizacion, departamento
        );
        if(personas.isEmpty()){
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(departamento).concat(" en ").concat(organizacion));
        }
        return personas;
    }
}
