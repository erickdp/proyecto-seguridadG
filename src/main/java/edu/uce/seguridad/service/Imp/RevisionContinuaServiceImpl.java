package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.RevisionContinua;
import edu.uce.seguridad.repository.RevisionContinuaRepo;
import edu.uce.seguridad.service.service.RevisionContinuaService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RevisionContinuaServiceImpl implements RevisionContinuaService {

    private RevisionContinuaRepo repository;
    private EstadoCompletadoServiceImpl estadoCompletadoService;

    @Override
    public List<RevisionContinua> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public RevisionContinua agregar(RevisionContinua pojo) throws DataAccessException {
        return repository.insert(pojo);
    }

    @Override
    public RevisionContinua actualizar(RevisionContinua pojo) throws DataAccessException {
        RevisionContinua aux = this.repository.save(pojo);
        estadoCompletadoService.verificarEstadoPaso10(pojo.getUsuario());
        return aux;
    }

    @Override
    public RevisionContinua buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        RevisionContinua revision = this.buscaPorId(identificador);
        if (revision != null) {
            this.repository.delete(revision);
            estadoCompletadoService.verificarEstadoPaso10(revision.getUsuario());
        }
    }

    @Override
    public RevisionContinua buscarRevisionPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        return this.repository.findRevisionByUsuario(nombreUsuario);
    }

    @Override
    public void eliminarRespuestaFormularioRevisionContinua(String nombreUsuario) {
        RevisionContinua revision = this.repository.findRevisionByUsuario(nombreUsuario);
        if (revision != null) {
            this.repository.delete(revision);
            estadoCompletadoService.verificarEstadoPaso10(revision.getUsuario());
        }
    }
}
