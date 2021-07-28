package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.MiClaseException;
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
    public Persona buscarPersonaPorUsuarioYContrasena(String nombreUsuario, String contrasena) {
        return this.personaRepository.findPersonaByUsuarioYContrasena(nombreUsuario, contrasena);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorOrganizacion(String organizacion) {
        return this.personaRepository.findPersonaByOrganizacion(organizacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorRol(String role) {
        return this.personaRepository.findPersonaByRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorRoleYOrganizacion(String role, String organizacion) {
        return this.personaRepository.findPersonaByRolAndOrganization(role, organizacion);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPersonaPorUsuario(String nombreUsuario) {
        return this.personaRepository.findPersonaByUsuario(nombreUsuario);
    }

    @Override
    @Transactional
    public String eliminarPersonaPorNombreUsuario(String nombreUsuario) throws MiClaseException {
        Persona persona = this.buscarPersonaPorUsuario(nombreUsuario);
        if (persona == null) {
            throw new MiClaseException("No se encontró al Usuario: ".concat(nombreUsuario));
        }
        this.personaRepository.deleteById(persona.get_id());
        return "Eliminado correctamente.";
    }

    @Override
    public List<Persona> buscarPersonasPorOrganizacionYDepartamento(String organizacion, String departamento) {
        return this.personaRepository.findPersonaByOrganizacionAndDepartamento(
                organizacion, departamento
        );
    }
}
