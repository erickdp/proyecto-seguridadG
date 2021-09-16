package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.CentroOperacion;
import edu.uce.seguridad.repository.CentroOperacionRepository;
import edu.uce.seguridad.service.service.CentroOperacionService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CentroOperacionServiceImpl implements CentroOperacionService {
    private CentroOperacionRepository repository;

    @Override
    public List<CentroOperacion> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public CentroOperacion agregar(CentroOperacion pojo) throws DataAccessException {
        return repository.insert(pojo);
    }

    @Override
    public CentroOperacion actualizar(CentroOperacion pojo) throws DataAccessException {
        return this.repository.save(pojo);
    }

    @Override
    public CentroOperacion buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        CentroOperacion centros = this.buscaPorId(identificador);
        if (centros != null) {
            this.repository.delete(centros);
        }
    }

    @Override
    public CentroOperacion buscarCentroPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        return this.repository.findCentroByUsuario(nombreUsuario);
    }

    @Override
    public void eliminarPorUsuario(String usuario) {
        CentroOperacion centroOperacion = this.buscarCentroPorUsuario(usuario);
        if (centroOperacion != null) {
            this.repository.delete(centroOperacion);
        }
    }
}
