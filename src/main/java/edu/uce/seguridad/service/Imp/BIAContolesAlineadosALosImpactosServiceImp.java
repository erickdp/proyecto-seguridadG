package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAControlesAlineadosALosImpactos;
import edu.uce.seguridad.repository.BIAControlesAlineadosALosImpactosRepository;
import edu.uce.seguridad.service.service.BIAControlesAlineadosALosImpactosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BIAContolesAlineadosALosImpactosServiceImp implements BIAControlesAlineadosALosImpactosService{

        @Autowired
    private BIAControlesAlineadosALosImpactosRepository repository;
        
    @Override
    @Transactional(readOnly = true)
    public List<BIAControlesAlineadosALosImpactos> buscarPorUserFiltrarPorInmueble(String user) throws NoEncontradoExcepcion {
              List<BIAControlesAlineadosALosImpactos> contactos = this.repository.findByUserOrderByInmueble(user);
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return contactos;}

    @Override
     @Transactional
    public void eliminarPorInmueble(String user) {
        List<BIAControlesAlineadosALosImpactos> contactos = this.repository.findByUserOrderByInmueble(user);
        if (!contactos.isEmpty()) {
            contactos.forEach(contacto -> this.eliminarDocumento(contacto.get_id()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BIAControlesAlineadosALosImpactos> buscarTodos() throws NoEncontradoExcepcion {
            List<BIAControlesAlineadosALosImpactos> contactos = this.repository.findAll();
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return contactos;}

    @Override
    @Transactional
    public BIAControlesAlineadosALosImpactos agregar(BIAControlesAlineadosALosImpactos pojo) throws DataAccessException {
        return this.repository.insert(pojo);
    }

    @Override
    @Transactional
    public BIAControlesAlineadosALosImpactos actualizar(BIAControlesAlineadosALosImpactos pojo) throws DataAccessException {
             this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public BIAControlesAlineadosALosImpactos buscaPorId(String identificador) throws NoEncontradoExcepcion {
            BIAControlesAlineadosALosImpactos contactos = this.repository.findById(identificador).orElse(null);
        if (contactos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return contactos; 
    }

    @Override
     @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
            BIAControlesAlineadosALosImpactos contatos = this.buscaPorId(identificador); 
        if (contatos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(contatos);
    }

    @Override
    @Transactional
    public void eliminarConUsuario(String user) {
        List<BIAControlesAlineadosALosImpactos> contatos = this.repository.findByUserOrderByInmueble(user);
        if (!contatos.isEmpty()) {
            this.repository.deleteAll(contatos);
        }
    }
}
