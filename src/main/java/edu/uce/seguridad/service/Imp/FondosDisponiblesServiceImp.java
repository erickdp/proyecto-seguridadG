package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EstatusFinanciero;
import edu.uce.seguridad.model.FondosDisponibles;
import edu.uce.seguridad.model.FondosDistribucion;
import edu.uce.seguridad.repository.EstatusFinancieroRepository;
import edu.uce.seguridad.repository.FondosDisponiblesRepository;
import edu.uce.seguridad.service.service.FondosDisponiblesService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static edu.uce.seguridad.util.Utileria.getFondoTotal;
import static edu.uce.seguridad.util.Utileria.calcularBalance;

@Service
@AllArgsConstructor
public class FondosDisponiblesServiceImp implements FondosDisponiblesService {

    private FondosDisponiblesRepository fondosDisponiblesRepository;

    private EstatusFinancieroRepository financieroRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FondosDisponibles> buscarTodos() throws NoEncontradoExcepcion {
        List<FondosDisponibles> list = this.fondosDisponiblesRepository.findAll();
        if (list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No existen regsitros");
        }
        return list;
    }

    @Override
    @Transactional
    public FondosDisponibles agregar(FondosDisponibles pojo) throws DataAccessException {
        EstatusFinanciero status = this.financieroRepository.findByUsuario(pojo.getUsaurio());
        if (status == null) { // En el caso de que no exista genero un estado financiero solo con el valor del total A y sin balanceABC
            status = new EstatusFinanciero();
            status.setUsuario(pojo.getUsaurio());
            status.setFondosDisponiblesA(pojo.getTotalFondos());
        } else { // Si ya existe entonces agrego el nuevo valor del total A y calculo el nuevo balance
            status.setFondosDisponiblesA(pojo.getTotalFondos());
            status.setBalanceABC(calcularBalance(status));
        }
        this.financieroRepository.save(status); // Guardo el estado financiero para que si de fondos disponibles va hacia la pestana de estatus solo tenga el estado A
        // En el front se realiza la suma de Total Fondos Disponibles (A)
        return this.fondosDisponiblesRepository.insert(pojo);
    }

    @Override
    @Transactional
    public FondosDisponibles actualizar(FondosDisponibles pojo) throws DataAccessException {
        // Si es estado financiero esta ya echo entonces toma la nueva variable de FondosDisponibles totalFondos;
        EstatusFinanciero estatus = this.financieroRepository.findByUsuario(pojo.getUsaurio()); // Se debe generar un nuevo usuario para que se creen todos los registros automáticos
        if (estatus != null) {
            estatus.setFondosDisponiblesA(pojo.getTotalFondos()); // el total se gurada en una variable primitiva double y eso se setea en el estado
            estatus.setBalanceABC(calcularBalance(estatus));
            this.financieroRepository.save(estatus);
        }
        return this.fondosDisponiblesRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public FondosDisponibles buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Optional<FondosDisponibles> fondosDisponibles = this.fondosDisponiblesRepository.findByUsaurio(identificador);
        if (fondosDisponibles.isPresent()) {
            return fondosDisponibles.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No hay registros para : ".concat(identificador));
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        Optional<FondosDisponibles> fondosDisponibles = this.fondosDisponiblesRepository.findByUsaurio(identificador);
        fondosDisponibles.ifPresent(this.fondosDisponiblesRepository::delete);
    }

    @Override
    public void eliminarConUsuario(String usuario) {
        Optional<FondosDisponibles> fondosDisponibles = this.fondosDisponiblesRepository.findByUsaurio(usuario);
        fondosDisponibles.ifPresent(elemento -> this.fondosDisponiblesRepository.delete(fondosDisponibles.get()));
    }
}
