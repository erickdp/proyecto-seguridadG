package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ActividadesPrioritarias;
import edu.uce.seguridad.repository.ActividadesPrioritariasRepository;
import edu.uce.seguridad.service.service.ActividadesPrioritariasService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActividadesPrioritariasServiceImpl implements ActividadesPrioritariasService {

    @Autowired
    private ActividadesPrioritariasRepository repository;

    @Override
    public List<ActividadesPrioritarias> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public ActividadesPrioritarias agregar(ActividadesPrioritarias pojo) throws DataAccessException {
        return repository.insert(pojo);
    }

    @Override
    public ActividadesPrioritarias actualizar(ActividadesPrioritarias pojo) throws DataAccessException {
        return this.repository.save(pojo);
    }

    @Override
    public ActividadesPrioritarias buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        ActividadesPrioritarias actividades = this.buscaPorId(identificador);
        if (actividades != null) {
            this.repository.delete(actividades);
        }
    }

    @Override
    public List<ActividadesPrioritarias> buscarActividadesPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        return this.repository.findActividadesByUsuario(nombreUsuario);
    }
}
