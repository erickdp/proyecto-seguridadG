package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EstatusFinanciero;
import edu.uce.seguridad.repository.EstatusFinancieroRepository;
import edu.uce.seguridad.service.service.EstatusFinancieroService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstatusFinancieroServiceImp implements EstatusFinancieroService {

    private EstatusFinancieroRepository financieronRepository;

    @Override
    public List<EstatusFinanciero> buscarTodos() throws NoEncontradoExcepcion {
        List<EstatusFinanciero> lista = this.financieronRepository.findAll();
        if (lista.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No existen registros");
        }
        return lista;
    }

    @Override
    public EstatusFinanciero agregar(EstatusFinanciero pojo) throws DataAccessException {
        return this.financieronRepository.insert(pojo);
    }

    @Override
    public EstatusFinanciero actualizar(EstatusFinanciero pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.financieronRepository.save(pojo);
    }

    @Override
    public EstatusFinanciero buscaPorId(String identificador) throws NoEncontradoExcepcion {
        EstatusFinanciero esFin = this.financieronRepository.findById(identificador).orElse(null);
        if (esFin == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return esFin;
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        EstatusFinanciero esFin = this.buscaPorId(identificador);
        if (esFin == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        this.financieronRepository.delete(esFin);
    }

    @Override
    public EstatusFinanciero buscarPorUsuario(String usuario) {
        EstatusFinanciero estatusFinanciero = this.financieronRepository.findByUsuario(usuario);
        if (estatusFinanciero == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return estatusFinanciero;
    }

    @Override
    public void eliminarPorUsuario(String nombreUsuario) {
        EstatusFinanciero estatusFinanciero = this.financieronRepository.findByUsuario(nombreUsuario);
        if (estatusFinanciero != null) {
            this.financieronRepository.deleteByUsuario(nombreUsuario);
        }
    }
}
