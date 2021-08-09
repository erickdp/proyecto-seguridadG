package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EstimacionDano;
import edu.uce.seguridad.repository.EstimacionDanoRepository;
import edu.uce.seguridad.service.service.EstimacionDanoService;
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
    public EstimacionDano buscarFormularioEstimacionPorUsuario(String usuario) throws NoEncontradoExcepcion {
        Optional<EstimacionDano> formularioEstimacion = this.estimacionDanoRepository.findByUsuario(usuario);
        if(formularioEstimacion.isPresent()) {
            return formularioEstimacion.get();
        } else {
            // Aqui debe de ir el proceso para agregar las categorias definidas en 3.1 y el otor
        }
        throw new NoEncontradoExcepcion("mensaje", "No se ha encontrado registor de :".concat(usuario));
    }
}
