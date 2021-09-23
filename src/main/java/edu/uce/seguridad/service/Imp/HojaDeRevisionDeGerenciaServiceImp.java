package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.HojaDeRevisionDeGerencia;
import edu.uce.seguridad.repository.HojaDeRevisionDeGerenciaRepository;
import edu.uce.seguridad.service.service.HojaDeRevisionDeGerenciaService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class HojaDeRevisionDeGerenciaServiceImp implements HojaDeRevisionDeGerenciaService{

    private HojaDeRevisionDeGerenciaRepository repository;
    private EstadoCompletadoServiceImpl estadoCompletadoService;
    
    @Override
    public List<HojaDeRevisionDeGerencia> buscarPorUserFiltrarPorAsuntoARevisaryVerificar(String user) throws NoEncontradoExcepcion {
            List<HojaDeRevisionDeGerencia> contactos = this.repository.findByUserOrderByAsuntoARevisaryVerificar(user);
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarPorAsuntoARevisaryVerificar(String user) {
            List<HojaDeRevisionDeGerencia> contactos = this.repository.findByUserOrderByAsuntoARevisaryVerificar(user);
        if (!contactos.isEmpty()) {
            contactos.forEach(contacto -> this.eliminarDocumento(contacto.get_id()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<HojaDeRevisionDeGerencia> buscarTodos() throws NoEncontradoExcepcion {
            List<HojaDeRevisionDeGerencia> contactos = this.repository.findAll();
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return contactos;
    }
    

    @Override
    public HojaDeRevisionDeGerencia agregar(HojaDeRevisionDeGerencia pojo) throws DataAccessException {
        HojaDeRevisionDeGerencia aux = this.repository.insert(pojo);
        estadoCompletadoService.verificarEstadoPaso10(pojo.getUser());
       return aux;
    }

    @Override
    @Transactional
    public HojaDeRevisionDeGerencia actualizar(HojaDeRevisionDeGerencia pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public HojaDeRevisionDeGerencia buscaPorId(String identificador) throws NoEncontradoExcepcion {
        HojaDeRevisionDeGerencia contactos = this.repository.findById(identificador).orElse(null);
        if (contactos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        HojaDeRevisionDeGerencia contatos = this.buscaPorId(identificador);
        if (contatos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(contatos);
        estadoCompletadoService.verificarEstadoPaso10(contatos.getUser());
    }
        
    
}
