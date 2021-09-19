package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EstatusFinanciero;
import edu.uce.seguridad.model.FormularioCostosRecup;
import edu.uce.seguridad.repository.EstatusFinancieroRepository;
import edu.uce.seguridad.repository.FormularioCostosRecupRepository;
import edu.uce.seguridad.service.service.FormularioCostosRecupService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.uce.seguridad.util.Utileria.calcularBalance;

@AllArgsConstructor
@Service
public class FormularioCostosRecupServiceImp implements FormularioCostosRecupService {

    private FormularioCostosRecupRepository formularioCostosRecupRepository;

    private EstatusFinancieroRepository financieroRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioCostosRecup> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioCostosRecup> lista = this.formularioCostosRecupRepository.findAll();
        if (lista.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return lista;
    }

    @Override
    public FormularioCostosRecup agregar(FormularioCostosRecup pojo) throws DataAccessException {
        EstatusFinanciero status = this.financieroRepository.findByUsuario(pojo.getUsuario());
        if (status == null) { // En el caso de que no exista genero un estado financiero solo con el valor del total B y sin balanceABC
            status = new EstatusFinanciero();
            status.setUsuario(pojo.getUsuario());
            status.setCostoRecuperacionB(pojo.getTotalCosto());
        } else { // Si ya existe entonces agrego el nuevo valor del total B y calculo el nuevo balance
            status.setCostoRecuperacionB(pojo.getTotalCosto());
            status.setBalanceABC(calcularBalance(status));
        }
        this.financieroRepository.save(status); // Guardo el estado financiero para que si de fondos disponibles va hacia la pestana de estatus solo tenga el estado B
        // En el front se realiza la suma de Total (B)
        return this.formularioCostosRecupRepository.insert(pojo);
    }

    @Override
    public FormularioCostosRecup actualizar(FormularioCostosRecup pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id()); // Esto no es necesario si el nombre del ususario está indexado como único en el objeto, por tanto no se crean 2 registros distintos By Erick


        EstatusFinanciero estatus = this.financieroRepository.findByUsuario(pojo.getUsuario()); // Se debe generar un nuevo usuario para que se creen todos los registros automáticos
        if (estatus != null) {
            estatus.setCostoRecuperacionB(pojo.getTotalCosto()); // cast a double (ver si es mejor el uso de una variable para que sea dinámico la suma en el front)
            estatus.setBalanceABC(calcularBalance(estatus));
            this.financieroRepository.save(estatus);
        }


        return this.formularioCostosRecupRepository.save(pojo);
    }

    @Override
    public FormularioCostosRecup buscaPorId(String identificador) throws NoEncontradoExcepcion {
        FormularioCostosRecup formularioCostosRecup = this.formularioCostosRecupRepository.findById(identificador).orElse(null);
        if (formularioCostosRecup == null) {
            throw new NoEncontradoExcepcion("respuesta", "EL registro con el id: ".concat(identificador) + "No se ha encontrado");
        }
        return formularioCostosRecup;
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        FormularioCostosRecup formCost = this.buscaPorId(identificador);
        if (formCost == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se ha encontrado el Documento");
        }
        this.formularioCostosRecupRepository.delete(formCost);
    }

    @Override
    public FormularioCostosRecup buscarPorUsuario(String usuario) {
        FormularioCostosRecup listadeCostos = this.formularioCostosRecupRepository.findByUsuario(usuario);
        if (listadeCostos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se encontro el formulario para el usuario:".concat(usuario));
        }
        return listadeCostos;
    }

    @Override
    public void eliminarPorUsuario(String usuario) {
        FormularioCostosRecup formCost = this.buscarPorUsuario(usuario);
        if (formCost == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se ha encontrado el Documento");
        }
        this.formularioCostosRecupRepository.deleteByUsuario(usuario);
    }

    @Override
    public void eliminarConUsuario(String usuario) {
        FormularioCostosRecup formCost = this.buscarPorUsuario(usuario);
        if (formCost != null) {
            this.formularioCostosRecupRepository.delete(formCost);
        }
    }
}
