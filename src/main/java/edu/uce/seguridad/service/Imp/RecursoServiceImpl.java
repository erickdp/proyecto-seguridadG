package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Recurso;
import edu.uce.seguridad.repository.RecursoRepository;
import edu.uce.seguridad.service.service.RecursoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecursoServiceImpl implements RecursoService {

    private RecursoRepository repository;

    @Override
    public List<Recurso> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public Recurso agregar(Recurso pojo) throws DataAccessException {
        return repository.insert(pojo);
    }

    @Override
    public Recurso actualizar(Recurso pojo) throws DataAccessException {
        return this.repository.save(pojo);
    }

    @Override
    public Recurso buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) {
        Recurso recurso = this.buscarRecursoPorUsuario(identificador);
        if (recurso != null) {
            this.repository.delete(recurso);
        }
    }

    @Override
    public Recurso buscarRecursoPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        return this.repository.findRecursoByUsuario(nombreUsuario);
    }
}
