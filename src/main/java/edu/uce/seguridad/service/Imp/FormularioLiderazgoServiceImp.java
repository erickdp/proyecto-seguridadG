package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioLiderazgo;
import edu.uce.seguridad.model.FormularioRevision;
import edu.uce.seguridad.model.RevisionContinua;
import edu.uce.seguridad.repository.FormularioLiderazgoRepository;
import edu.uce.seguridad.repository.RevisionContinuaRepo;
import edu.uce.seguridad.service.service.FormularioLiderazgoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.uce.seguridad.util.Utileria.formulariosDefault;

@Service
@AllArgsConstructor
public class FormularioLiderazgoServiceImp implements FormularioLiderazgoService {

    private FormularioLiderazgoRepository formularioLiderazgoRepository;

    private RevisionContinuaRepo revisionContinuaRepo;

    private EstadoCompletadoServiceImpl estadoCompletadoService;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioLiderazgo> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioLiderazgo> formularioLi = this.formularioLiderazgoRepository.findAll();
        if (formularioLi.isEmpty()) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros");

        }
        return formularioLi;
    }

    @Override
    @Transactional
    public FormularioLiderazgo agregar(FormularioLiderazgo pojo) throws DataAccessException {

        FormularioLiderazgo insertado = this.formularioLiderazgoRepository.insert(pojo);

        estadoCompletadoService.verificarEstadoPaso1(pojo.getUser());

        return insertado;
    }

    @Override
    @Transactional
    public FormularioLiderazgo actualizar(FormularioLiderazgo pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.formularioLiderazgoRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioLiderazgo buscaPorId(String identificador) throws NoEncontradoExcepcion {
        FormularioLiderazgo formu2 = this.formularioLiderazgoRepository.findById(identificador).orElse(null);
        if (formu2 == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros");
        }
        return formu2;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        FormularioLiderazgo formLide = this.buscaPorId(identificador);
        if (formLide == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros");
        }
        this.formularioLiderazgoRepository.delete(formLide);
        estadoCompletadoService.verificarEstadoPaso1(formLide.getUser());
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioLiderazgo buscarFormularioPorUsuario(String usuario) throws NoEncontradoExcepcion {
        FormularioLiderazgo formu3 = this.formularioLiderazgoRepository.findFormularioLiderazgoByUser(usuario);
        if (formu3 == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros para el usuario: ".concat(usuario));
        }
        return formu3;
    }

    // By Erick
    @Override
    @Transactional
    public void eliminarRespuestaFormularioLiderazgo(String nombreUsuario) {
        FormularioLiderazgo formularioLiderazgo = this.formularioLiderazgoRepository.findFormularioLiderazgoByUser(nombreUsuario);
        if (formularioLiderazgo != null) {
            this.formularioLiderazgoRepository.delete(formularioLiderazgo);
            estadoCompletadoService.verificarEstadoPaso1(formularioLiderazgo.getUser());
        }
    }


}
