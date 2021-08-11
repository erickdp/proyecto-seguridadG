package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioComparativoImpacto;
import edu.uce.seguridad.repository.FormularioImpactoRepository;
import edu.uce.seguridad.service.service.FormularioImpactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormularioImpactoServiceImpl  implements FormularioImpactoService {

    @Autowired
    private FormularioImpactoRepository formularioImpactoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioComparativoImpacto> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioComparativoImpacto> impacto= this.formularioImpactoRepository.findAll();
        if (impacto.isEmpty()){
            throw new NoEncontradoExcepcion("respuesta", "No se encontro el registro");
        }
        return impacto;
    }

    @Override
    @Transactional
    public FormularioComparativoImpacto agregar(FormularioComparativoImpacto pojo) throws DataAccessException {
        return this.formularioImpactoRepository.insert(pojo);
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
        FormularioComparativoImpacto impactos= this.formularioImpactoRepository.findById(identificador).orElse(null);
        if (impactos == null){
            throw new NoEncontradoExcepcion("respuesta", "No se ha encontrado el registro");
        }
        return impactos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        FormularioComparativoImpacto imp= this.buscaPorId(identificador);
        if (identificador == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se ha encontrado el registro");
        }
        this.formularioImpactoRepository.delete(imp);

    }

    @Override
    @Transactional(readOnly = true)
    public List<FormularioComparativoImpacto> buscarFormularioImpPorUsua(String usuario) throws NoEncontradoExcepcion{
        List<FormularioComparativoImpacto> impa = this.formularioImpactoRepository.findFormularioComparativoImpactoByUser(usuario);
        if (impa == null){
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros para el contacto :".concat(usuario));
        }
        return impa;
    }
}
