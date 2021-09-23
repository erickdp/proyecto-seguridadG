package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ActividadesPrioritarias;
import edu.uce.seguridad.repository.ActividadesPrioritariasRepository;
import edu.uce.seguridad.service.service.ActividadesPrioritariasService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActividadesPrioritariasServiceImpl implements ActividadesPrioritariasService {

    private ActividadesPrioritariasRepository repository;
    private EstadoCompletadoServiceImpl estadoCompletadoService;

    @Override
    public List<ActividadesPrioritarias> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public ActividadesPrioritarias agregar(ActividadesPrioritarias pojo) throws DataAccessException {
        ActividadesPrioritarias aux = repository.insert(pojo);
        estadoCompletadoService.verificarEstadoPaso2(pojo.getUsuario());
        return aux;
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
            estadoCompletadoService.verificarEstadoPaso2(actividades.getUsuario());
        }
    }

    @Override
    public List<ActividadesPrioritarias> buscarActividadesPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        return this.repository.findActividadesByUsuario(nombreUsuario);
    }

    @Override
    public void eliminarActividadUsuario(String nombreUsuario) {
        List<ActividadesPrioritarias> actividadesPrioritarias = this.repository.findActividadesByUsuario(nombreUsuario);
        if (!actividadesPrioritarias.isEmpty()) {
            actividadesPrioritarias.forEach(actividad -> this.eliminarDocumento(actividad.get_id()));
        }
    }
}
