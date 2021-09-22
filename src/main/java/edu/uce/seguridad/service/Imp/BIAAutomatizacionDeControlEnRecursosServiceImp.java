package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAAutomatizacionDeControlEnRecursos;
import edu.uce.seguridad.repository.BIAAutomatizacionDeControlEnRecursosRepository;
import edu.uce.seguridad.service.service.BIAAutomatizacionDeControlEnRecursosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BIAAutomatizacionDeControlEnRecursosServiceImp implements BIAAutomatizacionDeControlEnRecursosService {

    @Autowired
    private BIAAutomatizacionDeControlEnRecursosRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<BIAAutomatizacionDeControlEnRecursos> buscarPorUserFiltrarPorInmueble(String user) throws NoEncontradoExcepcion {
        List<BIAAutomatizacionDeControlEnRecursos> contactos = this.repository.findByUserOrderByInmueble(user);
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarPorInmueble(String user) {
        List<BIAAutomatizacionDeControlEnRecursos> contactos = this.repository.findByUserOrderByInmueble(user);
        if (!contactos.isEmpty()) {
            contactos.forEach(contacto -> this.eliminarDocumento(contacto.get_id()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BIAAutomatizacionDeControlEnRecursos> buscarTodos() throws NoEncontradoExcepcion {
        List<BIAAutomatizacionDeControlEnRecursos> contactos = this.repository.findAll();
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return contactos;
    }

    @Override
    @Transactional
    public BIAAutomatizacionDeControlEnRecursos agregar(BIAAutomatizacionDeControlEnRecursos pojo) throws DataAccessException {
        return this.repository.insert(pojo);
    }

    @Override
    @Transactional
    public BIAAutomatizacionDeControlEnRecursos actualizar(BIAAutomatizacionDeControlEnRecursos pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public BIAAutomatizacionDeControlEnRecursos buscaPorId(String identificador) throws NoEncontradoExcepcion {
        BIAAutomatizacionDeControlEnRecursos contactos = this.repository.findById(identificador).orElse(null);
        if (contactos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        BIAAutomatizacionDeControlEnRecursos contatos = this.buscaPorId(identificador);
        if (contatos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(contatos);
    }

    @Override
    @Transactional
    public void eliminarConUsuario(String user) {
        List<BIAAutomatizacionDeControlEnRecursos> contatos = this.repository.findByUserOrderByInmueble(user);
        if (!contatos.isEmpty()) {
            contatos.forEach(element -> this.repository.delete(element));
        }
    }

}
