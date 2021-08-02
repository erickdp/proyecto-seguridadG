package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioLiderazgo;
import edu.uce.seguridad.repository.FormularioLiderazgoRepository;
import edu.uce.seguridad.service.service.FormularioLiderazgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormularioLiderazgoServiceImp implements FormularioLiderazgoService {

    @Autowired
    private FormularioLiderazgoRepository formularioLiderazgoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioLiderazgo> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioLiderazgo> formularioLi= this.formularioLiderazgoRepository.findAll();
        if (formularioLi.isEmpty()){
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros");

        }
        return formularioLi;
    }

    @Override
    @Transactional
    public FormularioLiderazgo agregar(FormularioLiderazgo pojo)  throws  DataAccessException{
        return this.formularioLiderazgoRepository.insert(pojo);
    }

    @Override
    @Transactional
    public FormularioLiderazgo actualizar(FormularioLiderazgo pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.formularioLiderazgoRepository.save(pojo);
    }

    @Override
    @Transactional (readOnly = true)
    public FormularioLiderazgo buscaPorId(String identificador) throws  NoEncontradoExcepcion{
        FormularioLiderazgo formu2= this.formularioLiderazgoRepository.findById(identificador).orElse(null);
        if (formu2 == null){
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros");
        }
        return formu2;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
     FormularioLiderazgo formLide = this.buscaPorId(identificador);
     if (formLide == null){
         throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros");
     }
        this.formularioLiderazgoRepository.delete(formLide);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioLiderazgo buscarFormularioPorUsuario(String usuario) throws  NoEncontradoExcepcion{
        FormularioLiderazgo formu3 = this.formularioLiderazgoRepository.findByUser(usuario);
        if (formu3 == null){
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado registros para el usuario: ".concat(usuario));
        }
        return formu3;
    }

    @Override
    @Transactional
    public void eliminarRespuestaFormularioLiderazgo(String nombreUsuario) {
        FormularioLiderazgo formularioLiderazgo = this.buscarFormularioPorUsuario(nombreUsuario);
        this.formularioLiderazgoRepository.delete(formularioLiderazgo);
    }


}
