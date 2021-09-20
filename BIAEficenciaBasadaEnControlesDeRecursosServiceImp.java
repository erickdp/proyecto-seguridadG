package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAEficenciaBasadaEnControlesDeRecursos;
import edu.uce.seguridad.repository.BIAEficenciaBasadaEnControlesDeRecursosRepository;
import edu.uce.seguridad.service.service.BIAEficenciaBasadaEnControlesDeRecursosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BIAEficenciaBasadaEnControlesDeRecursosServiceImp implements BIAEficenciaBasadaEnControlesDeRecursosService{
     @Autowired
    private BIAEficenciaBasadaEnControlesDeRecursosRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<BIAEficenciaBasadaEnControlesDeRecursos> buscarPorUserFiltrarPorInmueble(String user) throws NoEncontradoExcepcion {
            List<BIAEficenciaBasadaEnControlesDeRecursos> contactos = this.repository.findByUserOrderByInmueble(user);
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return contactos;
    }

   /* @Override
    @Transactional
    public void eliminarPorInmueble(String user) {
           List<BIAEficenciaBasadaEnControlesDeRecursos> contactos = this.repository.findByUserOrderByInmueble(user);
        if (!contactos.isEmpty()) {
            contactos.forEach(contacto -> this.eliminarDocumento(contacto.get_id()));
        }
    } 
    */

    @Override
    @Transactional(readOnly = true)
    public List<BIAEficenciaBasadaEnControlesDeRecursos> buscarTodos() throws NoEncontradoExcepcion {
        List<BIAEficenciaBasadaEnControlesDeRecursos> contactos = this.repository.findAll();
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return contactos;
    }

    @Override
    @Transactional
    public BIAEficenciaBasadaEnControlesDeRecursos agregar(BIAEficenciaBasadaEnControlesDeRecursos pojo) throws DataAccessException {
        return this.repository.insert(pojo);
    }

    @Override
    @Transactional
    public BIAEficenciaBasadaEnControlesDeRecursos actualizar(BIAEficenciaBasadaEnControlesDeRecursos pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public BIAEficenciaBasadaEnControlesDeRecursos buscaPorId(String identificador) throws NoEncontradoExcepcion {
            BIAEficenciaBasadaEnControlesDeRecursos contactos = this.repository.findById(identificador).orElse(null);
        if (contactos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
            BIAEficenciaBasadaEnControlesDeRecursos contatos = this.buscaPorId(identificador);
        if (contatos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(contatos);
    }
    
    @Override
    @Transactional
    public void eliminarBIAEficenciaBasadaEnControlesDeRecursos(String nombreUsuario) throws NoEncontradoExcepcion{
        BIAEficenciaBasadaEnControlesDeRecursos contatos= (BIAEficenciaBasadaEnControlesDeRecursos) this.repository.findByUserOrderByInmueble(nombreUsuario);
        if (contatos != null) {
            this.repository.delete(contatos);
        }
    }
}
