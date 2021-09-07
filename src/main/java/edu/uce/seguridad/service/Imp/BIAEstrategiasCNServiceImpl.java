package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAEstrategiasCN;
import edu.uce.seguridad.repository.BIAEstrategiasCNRepo;
import edu.uce.seguridad.service.service.BIAEstrategiasCNService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BIAEstrategiasCNServiceImpl implements BIAEstrategiasCNService {
    private BIAEstrategiasCNRepo repository;

    @Override
    public List<BIAEstrategiasCN> buscarEstrategiasPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        return this.repository.findEstrategiasByUsuario(nombreUsuario);
    }

    @Override
    public List<BIAEstrategiasCN> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public BIAEstrategiasCN agregar(BIAEstrategiasCN pojo) throws DataAccessException {
        return repository.insert(pojo);
    }

    @Override
    public BIAEstrategiasCN actualizar(BIAEstrategiasCN pojo) throws DataAccessException {
        return this.repository.save(pojo);
    }

    @Override
    public BIAEstrategiasCN buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        BIAEstrategiasCN estrategiaCN = this.buscaPorId(identificador);
        if (estrategiaCN != null) {
            this.repository.delete(estrategiaCN);
        }
    }
}
