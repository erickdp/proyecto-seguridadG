package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioComparativoImpacto;
import edu.uce.seguridad.repository.FormularioImpactoRepository;
import edu.uce.seguridad.service.service.FormularioImpactoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FormularioImpactoServiceImpl implements FormularioImpactoService {

    private FormularioImpactoRepository formularioImpactoRepository;
    private EstadoCompletadoServiceImpl estadoCompletadoService;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioComparativoImpacto> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioComparativoImpacto> impacto = this.formularioImpactoRepository.findAll();
        if (impacto.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se encontro el registro");
        }
        return impacto;
    }

    @Override
    @Transactional
    public FormularioComparativoImpacto agregar(FormularioComparativoImpacto pojo) throws DataAccessException {
        FormularioComparativoImpacto aux = this.formularioImpactoRepository.insert(pojo);
        estadoCompletadoService.verificarEstadoPaso2(pojo.getUser());
        return aux;
    }

    @Override
    @Transactional
    public FormularioComparativoImpacto actualizar(FormularioComparativoImpacto pojo) throws DataAccessException {
        this.buscaPorId(pojo.getId());
        return this.formularioImpactoRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioComparativoImpacto buscaPorId(String identificador) throws NoEncontradoExcepcion {
        FormularioComparativoImpacto impactos = this.formularioImpactoRepository.findById(identificador).orElse(null);
        if (impactos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se ha encontrado el registro");
        }
        return impactos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        FormularioComparativoImpacto imp = this.buscaPorId(identificador);
        if (identificador == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se ha encontrado el registro");
        }
        this.formularioImpactoRepository.delete(imp);

    }

    @Override
    @Transactional(readOnly = true)
    public List<FormularioComparativoImpacto> buscarFormularioImpPorUsua(String usuario) throws NoEncontradoExcepcion {
        List<FormularioComparativoImpacto> impa = this.formularioImpactoRepository.findFormularioComparativoImpactoByUser(usuario);
        if (impa == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros para el contacto :".concat(usuario));
        }
        return impa;
    }

    @Override
    public void eliminarImpactosUsuario(String usuario) {
        List<FormularioComparativoImpacto> impactos = this.formularioImpactoRepository.findFormularioComparativoImpactoByUser(usuario);
        if (!impactos.isEmpty()) {
            this.formularioImpactoRepository.deleteAll(impactos);
        }
    }
}
