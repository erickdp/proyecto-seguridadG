package edu.uce.seguridad.service;

import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.repository.PersonaRepository;
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
    public void eliminarDocumento(String identificador) {
        Persona persona = this.buscaPorId(identificador);
        if(persona != null) {
            persona.getUsuario().setEstado((byte) 0);
            this.actualizar(persona);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPersonaPorUsuario(String nombreUsuario, String contrasena) {
        return this.personaRepository.findPersonaByUsuario(nombreUsuario, contrasena);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorOrganizacion(String organizacion) {
        return this.personaRepository.findPersonaByOrganizacion(organizacion);
    }
}
