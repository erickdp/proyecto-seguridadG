package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ImpactoNegocio;
import edu.uce.seguridad.repository.ImpactoRepository;
import edu.uce.seguridad.service.service.ImpactoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImpactoServiceImpl implements ImpactoService {

    private ImpactoRepository repository;

    @Override
    public List<ImpactoNegocio> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public ImpactoNegocio agregar(ImpactoNegocio pojo) throws DataAccessException {
        return repository.insert(pojo);
    }

    @Override
    public ImpactoNegocio actualizar(ImpactoNegocio pojo) throws DataAccessException {
        return this.repository.save(pojo);
    }

    @Override
    public ImpactoNegocio buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        ImpactoNegocio impactoNegocio = this.buscaPorId(identificador);
        this.repository.delete(impactoNegocio);
    }

    @Override
    public ImpactoNegocio buscarImpactoPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        return this.repository.findImpactoByUsuario(nombreUsuario);
    }

    @Override
    public void eliminarImpactosUsuario(String usuario) {
        List<ImpactoNegocio> impactos = this.repository.findByUsuarioNombreUsuario(usuario);
        if (!impactos.isEmpty()) {
            impactos.forEach(impacto -> this.eliminarDocumento(impacto.get_id()));
        }
    }
}
