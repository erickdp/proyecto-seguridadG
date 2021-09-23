package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ContactoEmergencia;
import edu.uce.seguridad.repository.ContactoEmergenciaRepo;
import edu.uce.seguridad.service.service.ContactoEmergenciaService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactoEmergenciaServImpl implements ContactoEmergenciaService {
    private ContactoEmergenciaRepo repository;
    private EstadoCompletadoServiceImpl estadoCompletadoService;

    @Override
    public List<ContactoEmergencia> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public ContactoEmergencia agregar(ContactoEmergencia pojo) throws DataAccessException {
        ContactoEmergencia aux = repository.insert(pojo);
        estadoCompletadoService.verificarEstadoPaso6(pojo.getUsuario());
        return aux;
    }

    @Override
    public ContactoEmergencia actualizar(ContactoEmergencia pojo) throws DataAccessException {
        return this.repository.save(pojo);
    }

    @Override
    public ContactoEmergencia buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        ContactoEmergencia contacto = this.buscaPorId(identificador);
        if (contacto != null) {
            this.repository.delete(contacto);
            estadoCompletadoService.verificarEstadoPaso6(contacto.getUsuario());
        }
    }

    @Override
    public ContactoEmergencia buscarContactoPorDepartamento(String nombreDepartamento) throws NoEncontradoExcepcion {
        return this.repository.findContactoByDepartamento(nombreDepartamento);
    }

    @Override
    public List<ContactoEmergencia> buscarContactosPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        return this.repository.findContactosByUsuario(nombreUsuario);
    }

    @Override
    public void eliminarConUsuario(String usuario) {
        List<ContactoEmergencia> contactoEmergencia = this.buscarContactosPorUsuario(usuario);
        if (!contactoEmergencia.isEmpty()) {
            contactoEmergencia.forEach(contactoEmergencia1 -> this.repository.delete(contactoEmergencia1));
            estadoCompletadoService.verificarEstadoPaso6(contactoEmergencia.get(0).getUsuario());
        }
    }
}
