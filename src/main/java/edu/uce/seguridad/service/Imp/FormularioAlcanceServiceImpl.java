package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioAlcance;
import edu.uce.seguridad.model.FormularioRevision;
import edu.uce.seguridad.model.RevisionContinua;
import edu.uce.seguridad.repository.FormularioAlcanceRepository;
import edu.uce.seguridad.repository.RevisionContinuaRepo;
import edu.uce.seguridad.service.service.FormularioAlcanceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.uce.seguridad.util.Utileria.formulariosDefault;

@Service
@AllArgsConstructor
public class FormularioAlcanceServiceImpl implements FormularioAlcanceService {

    private FormularioAlcanceRepository formularioAlcanceRepository;

    private RevisionContinuaRepo revisionContinuaRepo;

    private EstadoCompletadoServiceImpl estadoCompletadoService;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioAlcance> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioAlcance> alcance = this.formularioAlcanceRepository.findAll();
        if (alcance.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se encontro registro");
        }
        return alcance;
    }

    @Override
    @Transactional
    public FormularioAlcance agregar(FormularioAlcance pojo) throws DataAccessException {

        FormularioAlcance aux = this.formularioAlcanceRepository.insert(pojo);

        estadoCompletadoService.verificarEstadoPaso1(pojo.getUser());

        return aux;
    }

    @Override
    @Transactional
    public FormularioAlcance actualizar(FormularioAlcance pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.formularioAlcanceRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioAlcance buscaPorId(String identificador) throws NoEncontradoExcepcion {
        FormularioAlcance alcances = this.formularioAlcanceRepository.findById(identificador).orElse(null);
        if (alcances == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return alcances;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        FormularioAlcance formu = this.buscaPorId(identificador);
        if (identificador == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros");
        }
        this.formularioAlcanceRepository.delete(formu);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioAlcance buscarFormularioAlPorUsua(String usuario) throws NoEncontradoExcepcion {
        FormularioAlcance formu1 = this.formularioAlcanceRepository.findFormularioAlcanceByUser(usuario);
        if (formu1 == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros para el contacto :".concat(usuario));
        }
        return formu1;
    }

    // By Erick
    @Override
    @Transactional
    public void eliminarRespuestaFormularioAlcance(String nombreUsuario) {
        FormularioAlcance formularioAlcance = this.formularioAlcanceRepository.findFormularioAlcanceByUser(nombreUsuario);
        if (formularioAlcance != null) {
            this.formularioAlcanceRepository.delete(formularioAlcance);
        }
        // Si no encuentra el formulario no generar una excepcion para que siga el flujo normal de eliminacion en otros formularios.
    }
}
