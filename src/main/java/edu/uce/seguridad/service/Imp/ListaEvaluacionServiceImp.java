package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ListaEvaluacion;
import edu.uce.seguridad.repository.ListaEvaluacionRepository;
import edu.uce.seguridad.service.service.ListaEvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListaEvaluacionServiceImp implements ListaEvaluacionService {

    @Autowired
    private ListaEvaluacionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ListaEvaluacion> buscarTodos() throws NoEncontradoExcepcion {
        List<ListaEvaluacion> evaluaciones = this.repository.findAll();
        if (evaluaciones.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return evaluaciones;
    }

    @Override
    @Transactional
    public ListaEvaluacion agregar(ListaEvaluacion pojo) throws DataAccessException {
        return this.repository.insert(pojo);
    }

    @Override
    @Transactional
    public ListaEvaluacion actualizar(ListaEvaluacion pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public ListaEvaluacion buscaPorId(String identificador) throws NoEncontradoExcepcion {
        ListaEvaluacion evaluacion = this.repository.findById(identificador).orElse(null);
        if (evaluacion == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return evaluacion;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        ListaEvaluacion listaEvaluacion = this.buscaPorId(identificador);
        if (listaEvaluacion == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(listaEvaluacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListaEvaluacion> buscarPorUserFiltrarPorTipoCalidad(String user) throws NoEncontradoExcepcion {
        List<ListaEvaluacion> evaluaciones = this.repository.findByUserOrderByTipoCalidadAsc(user);
        if (evaluaciones.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(user));
        }
        return evaluaciones;
    }
}
