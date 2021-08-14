package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioListadeAcopio;
import edu.uce.seguridad.repository.FormularioListadeAcopioRepository;
import edu.uce.seguridad.service.service.FormularioListadeAcopioService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class FormularioListadeAcopioServiceImp implements FormularioListadeAcopioService {

    FormularioListadeAcopioRepository formularioListadeAcopioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioListadeAcopio> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioListadeAcopio> lista= this.formularioListadeAcopioRepository.findAll();
        if (lista.isEmpty()){
            throw  new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return lista;
    }

    @Override
    @Transactional
    public FormularioListadeAcopio agregar(FormularioListadeAcopio pojo) throws DataAccessException {
        return this.formularioListadeAcopioRepository.insert(pojo);
    }

    @Override
    @Transactional
    public FormularioListadeAcopio actualizar(FormularioListadeAcopio pojo) throws DataAccessException {
        this.buscaPorId(pojo.getId());
        return this.formularioListadeAcopioRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioListadeAcopio buscaPorId(String identificador) throws NoEncontradoExcepcion {
       FormularioListadeAcopio formularioListadeAcopio = this.formularioListadeAcopioRepository.findById(identificador).orElse(null);
       if (formularioListadeAcopio == null){
           throw new NoEncontradoExcepcion("respuesta", "EL registro con el id: " .concat(identificador) + "No se ha encontrado");
       }
       return  formularioListadeAcopio;
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
    FormularioListadeAcopio listadeAcopio = this.buscaPorId(identificador);
    if (listadeAcopio == null){
        throw  new NoEncontradoExcepcion("Respuesta", "No se ha encontrado el Documento");
    }
    this.formularioListadeAcopioRepository.delete(listadeAcopio);
    }

    @Override
    public List<FormularioListadeAcopio> buscarporUsuario(String usuario) {
        List<FormularioListadeAcopio> listadeAcopios = this.formularioListadeAcopioRepository.findByUsuario(usuario);
        if (listadeAcopios.isEmpty()){
            throw new NoEncontradoExcepcion("respuesta", "No se encontro el formulario para el usuario:" .concat(usuario));
        }
        return listadeAcopios;
    }

    @Override
    public void eliminarPorUsuario(String usuario) {
        List<FormularioListadeAcopio> listadeAcopios = buscarporUsuario(usuario);
        if (!listadeAcopios.isEmpty()){
            listadeAcopios.forEach(lista -> this.eliminarDocumento(lista.getId()));
        }

    }
}
