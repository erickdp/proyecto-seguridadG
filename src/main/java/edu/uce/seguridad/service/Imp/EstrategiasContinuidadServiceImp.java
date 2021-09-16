package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EstrategiasContinuidad;
import edu.uce.seguridad.repository.EstrategiasContinuidadRepository;
import edu.uce.seguridad.service.service.EstrategiasContinuidadService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EstrategiasContinuidadServiceImp implements EstrategiasContinuidadService {

    private EstrategiasContinuidadRepository estrategiasContinuidadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EstrategiasContinuidad> buscarTodos() throws NoEncontradoExcepcion {
        List<EstrategiasContinuidad> list = this.estrategiasContinuidadRepository.findAll();
        if(list.isEmpty()) {
            throw new NoEncontradoExcepcion("No se han encontrado datos");
        }
        return list;
    }

    @Override
    @Transactional
    public EstrategiasContinuidad agregar(EstrategiasContinuidad pojo) throws DataAccessException {
        return this.estrategiasContinuidadRepository.save(pojo);
    }

    @Override
    @Transactional
    public EstrategiasContinuidad actualizar(EstrategiasContinuidad pojo) throws DataAccessException {
        return this.estrategiasContinuidadRepository.save(pojo);
    }

    @Override
    public EstrategiasContinuidad buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return null;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        List<EstrategiasContinuidad> list = this.estrategiasContinuidadRepository.findByUsuario(identificador);
        if(!list.isEmpty()) {
            this.estrategiasContinuidadRepository.deleteAll(list);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstrategiasContinuidad> buscarPorUsuario(String usuario) throws NoEncontradoExcepcion{
        List<EstrategiasContinuidad> list = this.estrategiasContinuidadRepository.findByUsuario(usuario);
        if(list.isEmpty()) {
            throw new NoEncontradoExcepcion("No se han encontrado registros para: ".concat(usuario));
        }
        return list;
    }

    @Override
    public void eliminarConUsuario(String usuario) {
        List<EstrategiasContinuidad> list = this.buscarPorUsuario(usuario);
        if (!list.isEmpty()) {
            list.forEach(elemento -> this.estrategiasContinuidadRepository.delete(elemento));
        }
    }
}
