package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BiaValoracionGeneral;
import edu.uce.seguridad.repository.BiaValoracionGeneralRepository;
import edu.uce.seguridad.service.service.BiaValoracionGeneralService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BiaValoracionGeneralServiceImpl implements BiaValoracionGeneralService {

    private BiaValoracionGeneralRepository biaValoracionGeneralRepository;

    @Override
    public List<BiaValoracionGeneral> buscarTodos() throws NoEncontradoExcepcion {
        List<BiaValoracionGeneral> lista = this.biaValoracionGeneralRepository.findAll();
        if (lista.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se encuentran datos");
        }
        return lista;
    }

    @Override
    public BiaValoracionGeneral agregar(BiaValoracionGeneral pojo) throws DataAccessException {
        return this.biaValoracionGeneralRepository.insert(pojo);
    }

    @Override
    public BiaValoracionGeneral actualizar(BiaValoracionGeneral pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.biaValoracionGeneralRepository.save(pojo);
    }

    @Override
    public BiaValoracionGeneral buscaPorId(String identificador) throws NoEncontradoExcepcion {
        BiaValoracionGeneral biaValoracionGeneral = this.biaValoracionGeneralRepository.findById(identificador).orElse(null);
        if (biaValoracionGeneral == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se encuentran datos");
        }
        return biaValoracionGeneral;
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        BiaValoracionGeneral data = this.buscaPorId(identificador);
        this.biaValoracionGeneralRepository.delete(data);
    }

    @Override
    public BiaValoracionGeneral buscarPorUsuario(String usuario) {
        BiaValoracionGeneral data = this.biaValoracionGeneralRepository.findByUsuario(usuario);
        if (data == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se encuentran datos");
        }
        return data;
    }
}
