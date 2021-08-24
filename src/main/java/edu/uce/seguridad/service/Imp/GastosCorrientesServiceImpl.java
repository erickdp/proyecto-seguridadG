package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EstatusFinanciero;
import edu.uce.seguridad.model.GastosCorrientes;
import edu.uce.seguridad.repository.EstatusFinancieroRepository;
import edu.uce.seguridad.repository.GastosCorrientesRepository;
import edu.uce.seguridad.service.service.GastosCorrienteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GastosCorrientesServiceImpl implements GastosCorrienteService {

    private GastosCorrientesRepository gastosCorrientesRepository;

    private EstatusFinancieroRepository financieroRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GastosCorrientes> buscarTodos() throws NoEncontradoExcepcion {
        List<GastosCorrientes> gastosCorrientes = this.gastosCorrientesRepository.findAll();
        if (gastosCorrientes.isEmpty()) {
            throw new NoEncontradoExcepcion("Respuesta", "No se encontro registro");
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
        EstatusFinanciero estatus = this.financieroRepository.findByUsuario(pojo.getUser()); // Se debe generar un nuevo usuario para que se creen todos los registros automáticos
        if (estatus != null) {
            estatus.setGastosOrdinariosC(pojo.getTotalGastos()); // cast a double (ver si es mejor el uso de una variable para que sea dinámico la suma en el front)
            this.financieroRepository.save(estatus);
        }
        return this.gastosCorrientesRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public GastosCorrientes buscaPorId(String identificador) throws NoEncontradoExcepcion {
        GastosCorrientes gastos = this.gastosCorrientesRepository.findById(identificador).orElse(null);
        if (gastos == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registro para el id ".concat(identificador));
        }
        return gastos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        GastosCorrientes gastosCO = this.buscaPorId(identificador);
        if (gastosCO == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros");
        }
        this.gastosCorrientesRepository.delete(gastosCO);

    }

    @Override
    @Transactional(readOnly = true)
    public GastosCorrientes buscarGastosCorrientesPorUsuario(String usuario) {
        GastosCorrientes gasto = this.gastosCorrientesRepository.findByUser(usuario);
        if (gasto == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros para el usuario:".concat(usuario));
        }
        return gasto;
    }

    @Override
    public void eliminarPorUsuario(String usuario) {
        GastosCorrientes gastosCO = this.buscarGastosCorrientesPorUsuario(usuario);
        if (gastosCO != null) {
            this.gastosCorrientesRepository.delete(gastosCO);
        }
    }
}
