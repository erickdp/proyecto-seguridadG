package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAPeriocidadDeAccionesDeControlEnRecursos;
import edu.uce.seguridad.repository.BIAPeriocidadDeAccionesDeControlEnRecursosRepository;
import edu.uce.seguridad.service.service.BIAPeriocidadDeAccionesDeControlEnRecursosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BIAPeriocidadDeAccionesDeControlEnRecursosServiceImp implements BIAPeriocidadDeAccionesDeControlEnRecursosService {

    @Autowired
    private BIAPeriocidadDeAccionesDeControlEnRecursosRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<BIAPeriocidadDeAccionesDeControlEnRecursos> buscarPorUserFiltrarPorInmueble(String user) throws NoEncontradoExcepcion {
        List<BIAPeriocidadDeAccionesDeControlEnRecursos> contactos = this.repository.findByUserOrderByInmueble(user);
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarPorInmueble(String user) {
        List<BIAPeriocidadDeAccionesDeControlEnRecursos> contactos = this.repository.findByUserOrderByInmueble(user);
        if (!contactos.isEmpty()) {
            contactos.forEach(contacto -> this.eliminarDocumento(contacto.get_id()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BIAPeriocidadDeAccionesDeControlEnRecursos> buscarTodos() throws NoEncontradoExcepcion {
        List<BIAPeriocidadDeAccionesDeControlEnRecursos> contactos = this.repository.findAll();
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return contactos;
    }

    @Override
    @Transactional
    public BIAPeriocidadDeAccionesDeControlEnRecursos agregar(BIAPeriocidadDeAccionesDeControlEnRecursos pojo) throws DataAccessException {
        return this.repository.insert(pojo);
    }

    @Override
    @Transactional
    public BIAPeriocidadDeAccionesDeControlEnRecursos actualizar(BIAPeriocidadDeAccionesDeControlEnRecursos pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public BIAPeriocidadDeAccionesDeControlEnRecursos buscaPorId(String identificador) throws NoEncontradoExcepcion {
        BIAPeriocidadDeAccionesDeControlEnRecursos contactos = this.repository.findById(identificador).orElse(null);
        if (contactos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        BIAPeriocidadDeAccionesDeControlEnRecursos contatos = this.buscaPorId(identificador);
        if (contatos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(contatos);
    }

    @Override
    public void eliminarConUsuario(String user) {
            List<BIAPeriocidadDeAccionesDeControlEnRecursos> contatos = this.buscarPorUserFiltrarPorInmueble(user);
        if (!contatos.isEmpty()) {
            for (BIAPeriocidadDeAccionesDeControlEnRecursos contato : contatos) {
                this.repository.deletedByUser(user);
            }
        }}

}
