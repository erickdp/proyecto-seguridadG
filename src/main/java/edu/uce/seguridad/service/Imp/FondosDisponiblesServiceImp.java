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
        BigDecimal bigDecimal = new BigDecimal("0"); // En el back se raliza la suma de los montos para generar el monto final
        List<FondosDistribucion> fondosDistribucionList = pojo.getFondosDistribucion();
        for (FondosDistribucion fondo : fondosDistribucionList) {
            bigDecimal = bigDecimal.add(fondo.getMonto());
        }
        fondosDistribucionList.add(getFondoTotal(bigDecimal));
        return this.fondosDisponiblesRepository.insert(pojo);
    }

    @Override
    @Transactional
    public FondosDisponibles actualizar(FondosDisponibles pojo) throws DataAccessException {
        List<FondosDistribucion> fondosDistribucionList = pojo.getFondosDistribucion();
        BigDecimal bigDecimal = new BigDecimal("0");
        for (FondosDistribucion fondo : fondosDistribucionList) {
            if (!fondo.getTipo().equalsIgnoreCase("Total Fondos Disponibles (A)")) {
                bigDecimal = bigDecimal.add(fondo.getMonto());
            }
        }
        FondosDistribucion fondoTotal = fondosDistribucionList.get(fondosDistribucionList.size() - 1); // El monto total en la actualizacion debe de ser el ultimo registro de la lista
        fondoTotal.setMonto(bigDecimal);
        // Debería existir si o si porque se creo en pasos anteriores
        EstatusFinanciero estatus = this.financieroRepository.findByUsuario(pojo.getUsaurio()); // Se debe generar un nuevo usuario para que se creen todos los registros automáticos
        if (estatus != null) {
            estatus.setFondosDisponiblesA(bigDecimal.doubleValue()); // cast a double (ver si es mejor el uso de una variable para que sea dinámico la suma en el front)
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
}
