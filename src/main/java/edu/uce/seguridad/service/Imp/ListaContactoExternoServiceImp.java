package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ListaContactoExterno;
import edu.uce.seguridad.repository.ListaContactoExternoRepository;
import edu.uce.seguridad.service.service.ListaContactoExternoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ListaContactoExternoServiceImp implements ListaContactoExternoService{

    private ListaContactoExternoRepository repository;
    private EstadoCompletadoServiceImpl estadoCompletadoService;
 
    @Override
    @Transactional(readOnly = true)
    public List<ListaContactoExterno> buscarTodos() throws NoEncontradoExcepcion {
        List<ListaContactoExterno> contactos = this.repository.findAll();
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return contactos;
    }

    @Override
    @Transactional
    public ListaContactoExterno agregar(ListaContactoExterno pojo) throws DataAccessException {
        ListaContactoExterno aux = this.repository.insert(pojo);
        estadoCompletadoService.verificarEstadoPaso6(pojo.getUser());
        return aux;
    }

    @Override
    @Transactional
    public ListaContactoExterno actualizar(ListaContactoExterno pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public ListaContactoExterno buscaPorId(String identificador) throws NoEncontradoExcepcion {
        ListaContactoExterno contacto = this.repository.findById(identificador).orElse(null);
        if (contacto == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return contacto;
    }

    // Opcional poner un estado para no eliminarlo (no relacional)
    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        ListaContactoExterno contacto = this.repository.findById(identificador).orElse(null);
        if (contacto == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(contacto);
        estadoCompletadoService.verificarEstadoPaso6(contacto.getUser());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListaContactoExterno> buscarPorUserFiltrarPorTipoContacto(String user) throws NoEncontradoExcepcion {
        List<ListaContactoExterno> contactos = this.repository.findByUserOrderByTipoContactoAsc(user);
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return contactos;
    }

    // Propiedad de Erick es para que cuando elimine el usuario todos sus registros igual se eliminen
    // todos los derechos de autor para el 
    @Override
    public void eliminarConcatosPorUser(String user) {
        List<ListaContactoExterno> contactos = this.repository.findByUserOrderByTipoContactoAsc(user);
        if (!contactos.isEmpty()) {
            contactos.forEach(contacto -> this.repository.delete(contacto));
            estadoCompletadoService.verificarEstadoPaso6(contactos.get(0).getUser()); // desborde posible
        }
    }
}
