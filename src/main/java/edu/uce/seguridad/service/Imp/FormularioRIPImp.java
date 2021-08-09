package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioRIP;
import edu.uce.seguridad.repository.FormularioRIPRepository;
import edu.uce.seguridad.service.service.FormularioRIPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormularioRIPImp implements FormularioRIPService {

    @Autowired
    private FormularioRIPRepository formularioRIPRepository;


    @Override
    @Transactional(readOnly = true)
    public List<FormularioRIP> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioRIP> rips = this.formularioRIPRepository.findAll();
        if (rips.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return rips;
    }

    @Override
    @Transactional
    public FormularioRIP agregar(FormularioRIP pojo) throws DataAccessException {
        return this.formularioRIPRepository.insert(pojo);
    }

    @Override
    @Transactional
    public FormularioRIP actualizar(FormularioRIP pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.formularioRIPRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioRIP buscaPorId(String identificador) throws NoEncontradoExcepcion {
        FormularioRIP rip = this.formularioRIPRepository.findById(identificador).orElse(null);
        if (rip == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return rip;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        FormularioRIP rip = this.buscaPorId(identificador);
        if (rip == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.formularioRIPRepository.delete(rip);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormularioRIP> buscarPorUserFiltradoPorPrioridad(String user) throws NoEncontradoExcepcion {
        List<FormularioRIP> rips = this.formularioRIPRepository.findByUserOrderByPrioridadAsc(user);
        if (rips.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return rips;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormularioRIP> buscarPorUser(String user) throws NoEncontradoExcepcion {
        List<FormularioRIP> rips = this.formularioRIPRepository.findByUser(user);
        if (rips.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return rips;
    }

    @Override
    @Transactional
    public void eliminarPorUsusario(String user) {
        this.formularioRIPRepository.deleteByUser(user);
    }

    @Override // No trans porque delego ese proceso a otro metodo
    public FormularioRIP getMayorPrioridad(String user) {
        FormularioRIP formularioRIP = this.buscarPorUserFiltradoPorPrioridad(user).get(0);
        return formularioRIP;
    }
}
