package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioCostosRecup;
import edu.uce.seguridad.repository.FormularioCostosRecupRepository;
import edu.uce.seguridad.service.service.FormularioCostosRecupService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class FormularioCostosRecupServiceImp implements FormularioCostosRecupService {

    private FormularioCostosRecupRepository formularioCostosRecupRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioCostosRecup> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioCostosRecup> lista= this.formularioCostosRecupRepository.findAll();
        if (lista.isEmpty()){
            throw  new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return lista;
    }

    @Override
    public FormularioCostosRecup agregar(FormularioCostosRecup pojo) throws DataAccessException {
        return this.formularioCostosRecupRepository.insert(pojo);
    }

    @Override
    public FormularioCostosRecup actualizar(FormularioCostosRecup pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.formularioCostosRecupRepository.save(pojo);
    }

    @Override
    public FormularioCostosRecup buscaPorId(String identificador) throws NoEncontradoExcepcion {
        FormularioCostosRecup formularioCostosRecup = this.formularioCostosRecupRepository.findById(identificador).orElse(null);
        if (formularioCostosRecup == null){
            throw new NoEncontradoExcepcion("respuesta", "EL registro con el id: " .concat(identificador) + "No se ha encontrado");
        }
        return  formularioCostosRecup;
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        FormularioCostosRecup formCost = this.buscaPorId(identificador);
        if (formCost == null){
            throw  new NoEncontradoExcepcion("Respuesta", "No se ha encontrado el Documento");
        }
        this.formularioCostosRecupRepository.delete(formCost);
    }

    @Override
    public FormularioCostosRecup buscarPorUsuario(String usuario) {
        FormularioCostosRecup listadeCostos = this.formularioCostosRecupRepository.findByUsuario(usuario);
        if (listadeCostos == null){
            throw new NoEncontradoExcepcion("respuesta", "No se encontro el formulario para el usuario:" .concat(usuario));
        }
        return listadeCostos;
    }

    @Override
    public void eliminarPorUsuario(String usuario) {
        FormularioCostosRecup formCost = this.buscarPorUsuario(usuario);
        if (formCost == null){
            throw  new NoEncontradoExcepcion("Respuesta", "No se ha encontrado el Documento");
        }
        this.formularioCostosRecupRepository.deleteByUsuario(usuario);
    }
}
