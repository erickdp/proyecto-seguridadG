package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ListaContacto;
import edu.uce.seguridad.repository.ListaContactoRepository;
import edu.uce.seguridad.service.service.ListaContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListaContactoServiceImp implements ListaContactoService {

    @Autowired
    private ListaContactoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ListaContacto> buscarTodos() throws NoEncontradoExcepcion {
        List<ListaContacto> contactos = this.repository.findAll();
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return contactos;
    }

    @Override
    @Transactional
    public ListaContacto agregar(ListaContacto pojo) throws DataAccessException {
        return this.repository.insert(pojo);
    }

    @Override
    @Transactional
    public ListaContacto actualizar(ListaContacto pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public ListaContacto buscaPorId(String identificador) throws NoEncontradoExcepcion {
        ListaContacto contacto = this.repository.findById(identificador).orElse(null);
        if (contacto == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return contacto;
    }

    // Opcional poner un estado para no eliminarlo (no relacional)
    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        ListaContacto contacto = this.buscaPorId(identificador);
        if (contacto == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(contacto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListaContacto> buscarPorUserFiltrarPorTipoContacto(String user) throws NoEncontradoExcepcion {
        List<ListaContacto> contactos = this.repository.findByUserOrderByTipoContactoAsc(user);
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return contactos;
    }
}
