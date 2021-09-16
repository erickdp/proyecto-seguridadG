package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioEvaluacionDanosII;
import edu.uce.seguridad.repository.EvaluacionDanoRepository;
import edu.uce.seguridad.service.service.FormularioEvaluacionDanosIIService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FormularioEvaluacionDanosIIServiceImpl implements FormularioEvaluacionDanosIIService {

    private EvaluacionDanoRepository evaluacionDanoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioEvaluacionDanosII> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioEvaluacionDanosII> lista = this.evaluacionDanoRepository.findAll();
        if (lista.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se encontro registro");
        }
        return lista;
    }

    @Override
    @Transactional
    public FormularioEvaluacionDanosII agregar(FormularioEvaluacionDanosII pojo) throws DataAccessException {
        return this.evaluacionDanoRepository.insert(pojo);

    }

    @Override
    @Transactional
    public FormularioEvaluacionDanosII actualizar(FormularioEvaluacionDanosII pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.evaluacionDanoRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioEvaluacionDanosII buscaPorId(String identificador) throws NoEncontradoExcepcion {
        FormularioEvaluacionDanosII evaluacionDanos = this.evaluacionDanoRepository.findById(identificador).orElse(null);
        if (evaluacionDanos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se encontro registro");
        }
        return evaluacionDanos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        FormularioEvaluacionDanosII evaluacionDanos = this.buscaPorId(identificador);
        if (evaluacionDanos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se encontro registro");
        }
        this.evaluacionDanoRepository.delete(evaluacionDanos);
    }

    @Override
    public List<FormularioEvaluacionDanosII> buscarPorUsuario(String usuario) {
        List<FormularioEvaluacionDanosII> evaluacionDanos = this.evaluacionDanoRepository.findByUsuario(usuario);
        if (evaluacionDanos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se encontro registro");
        }
        return evaluacionDanos;
    }

    @Override
    public void eliminarEvaluacionesPorUser(String user) {
        List<FormularioEvaluacionDanosII> evaluacionDanos = buscarPorUsuario(user);
        if (!evaluacionDanos.isEmpty()) {
            evaluacionDanos.forEach(evaluacion -> this.evaluacionDanoRepository.delete(evaluacion));
        }
    }


}
