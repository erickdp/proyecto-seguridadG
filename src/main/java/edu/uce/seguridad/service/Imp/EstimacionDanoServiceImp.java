package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EstimacionDano;
import edu.uce.seguridad.model.FormularioRIP;
import edu.uce.seguridad.repository.EstimacionDanoRepository;
import edu.uce.seguridad.service.service.EstimacionDanoService;
import edu.uce.seguridad.service.service.FormularioRIPService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstimacionDanoServiceImp implements EstimacionDanoService {

    private EstimacionDanoRepository estimacionDanoRepository;

    private FormularioRIPService formularioRIPService;

    @Override
    @Transactional(readOnly = true)
    public List<EstimacionDano> buscarTodos() throws NoEncontradoExcepcion {
        List<EstimacionDano> list = this.estimacionDanoRepository.findAll();
        if(list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No existen registros");
        }
        return list;
    }

    @Override
    @Transactional
    public EstimacionDano agregar(EstimacionDano pojo) throws DataAccessException {
        return this.estimacionDanoRepository.insert(pojo);
    }

    @Override
    @Transactional
    public EstimacionDano actualizar(EstimacionDano pojo) throws DataAccessException {
        return this.estimacionDanoRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public EstimacionDano buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Optional<EstimacionDano> estimacionesDano = this.estimacionDanoRepository.findById(identificador);
        if(estimacionesDano.isPresent()) {
            return estimacionesDano.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros para: ".concat(identificador));
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        this.estimacionDanoRepository.deleteById(identificador);
    }


    @Override
    @Transactional(readOnly = true)
    public EstimacionDano buscarFormularioEstimacionPorUsuario(String usuario) {
        Optional<EstimacionDano> formularioEstimacion = this.estimacionDanoRepository.findByUsuario(usuario);
        if(formularioEstimacion.isPresent()) {
            return formularioEstimacion.get();
        } else {
            EstimacionDano estimacionDano = new EstimacionDano();
            estimacionDano.setUsuario(usuario);

            FormularioRIP mayorPrioridad = this.formularioRIPService.getMayorPrioridad(usuario);// Obtengo el form RIP ya llenado con mayor prioridad
            estimacionDano.setImpacto(mayorPrioridad.getImpacto()); // Los seteo para este nuevo formulario
            estimacionDano.setRiesgo(mayorPrioridad.getNombreRiesgo());
            estimacionDano.setProbabilidad(mayorPrioridad.getProbabilidad());

            // Falta la definicion de las actividades prioritarias que es el 3.1, solo setear los tipos en la lista, el resto solo deberia ir en 0

            return estimacionDano;
        }
    }
}
