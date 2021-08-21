package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.GastosCorrientes;
import edu.uce.seguridad.repository.GastosCorrientesRepository;
import edu.uce.seguridad.service.service.GastosCorrienteService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@NoArgsConstructor
public class GastosCorrientesServiceImpl implements GastosCorrienteService {

    @Autowired
    private GastosCorrientesRepository gastosCorrientesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GastosCorrientes> buscarTodos() throws NoEncontradoExcepcion {
        List<GastosCorrientes> gastosCorrientes = this.gastosCorrientesRepository.findAll();
        if (gastosCorrientes.isEmpty()){
            throw new NoEncontradoExcepcion("Respuesta","No se encontro registro");
        }
        return gastosCorrientes;
    }

    @Override
    @Transactional
    public GastosCorrientes agregar(GastosCorrientes pojo) throws DataAccessException {
        return this.gastosCorrientesRepository.insert(pojo);
    }

    @Override
    @Transactional
    public GastosCorrientes actualizar(GastosCorrientes pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.gastosCorrientesRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public GastosCorrientes buscaPorId(String identificador) throws NoEncontradoExcepcion {
        GastosCorrientes gastos = this.gastosCorrientesRepository.findById(identificador).orElse(null);
        if (gastos == null){
            throw new NoEncontradoExcepcion("Respuesta","No se han encontrado registro para el id " .concat(identificador));
        }
        return gastos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        GastosCorrientes gastosCO = this.buscaPorId(identificador);
        if (gastosCO == null){
            throw new NoEncontradoExcepcion("Respuesta","No se han encontrado registros");
        }
        this.gastosCorrientesRepository.delete(gastosCO);

    }

    @Override
    @Transactional(readOnly = true)
    public List<GastosCorrientes> buscarGastosCorrientesPorUsuario(String usuario) {
        List<GastosCorrientes> gasto = this.gastosCorrientesRepository.findByUser(usuario);
        if (gasto ==null){
            throw new NoEncontradoExcepcion("Respuesta","No se han encontrado registros para el usuario:" .concat(usuario));
        }
        return gasto;
    }
}
