package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Estimacion;
import edu.uce.seguridad.model.EstimacionDano;
import edu.uce.seguridad.model.FormularioRIP;
import edu.uce.seguridad.model.Recurso;
import edu.uce.seguridad.repository.FormularioRIPRepository;
import edu.uce.seguridad.service.service.EstimacionDanoService;
import edu.uce.seguridad.service.service.EvacuacionYRescateService;
import edu.uce.seguridad.service.service.FormularioRIPService;
import edu.uce.seguridad.service.service.RecursoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static edu.uce.seguridad.util.Utileria.establecerEstimaciones;

@Service
@AllArgsConstructor
public class FormularioRIPServiceImp implements FormularioRIPService {

    private FormularioRIPRepository formularioRIPRepository;

    private EstimacionDanoService estimacionDanoService;

    private RecursoService recursoService;

    private EvacuacionYRescateService evacuacionYRescateService;

    private EstadoCompletadoServiceImpl estadoCompletadoService;


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
        EstimacionDano estimacionDano = new EstimacionDano();
        estimacionDano.setUsuario(pojo.getUser());

        estimacionDano.setImpacto(pojo.getImpacto()); // Los seteo para este nuevo formulario
        estimacionDano.setRiesgo(pojo.getNombreRiesgo());
        estimacionDano.setProbabilidad(pojo.getProbabilidad());

        // En este paso NO se debe de crear el formulario 6.1 EvacuacionYRescate

        // se supone que ya deben estar ingresados los datos para poder recuperarlos
        Recurso recurso = this.recursoService.buscarRecursoPorUsuario(pojo.getUser());

        estimacionDano.setRecursosNecesarios(establecerEstimaciones(recurso));

        FormularioRIP aux = this.formularioRIPRepository.insert(pojo);
        estadoCompletadoService.verificarEstadoPaso4(pojo.getUser());
        this.estimacionDanoService.agregar(estimacionDano);
        return aux;
    }

    @Override
    @Transactional
    public FormularioRIP actualizar(FormularioRIP pojo) throws DataAccessException {
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
        estadoCompletadoService.verificarEstadoPaso4(rip.getUser());
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
        estadoCompletadoService.verificarEstadoPaso4(user);
    }

    @Override // No trans porque delego ese proceso a otro metodo
    public FormularioRIP getMayorPrioridad(String user) {
        FormularioRIP formularioRIP = this.buscarPorUserFiltradoPorPrioridad(user).get(0);
        return formularioRIP;
    }
}
