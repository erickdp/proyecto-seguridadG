package edu.uce.seguridad.service;

import edu.uce.seguridad.model.FormularioLiderazgo;
import edu.uce.seguridad.repository.FormularioLiderazgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormularioLiderazgoServiceImp implements  FormularioLiderazgoService {

    @Autowired
    private FormularioLiderazgoRepository formularioLiderazgoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioLiderazgo> buscarTodos() {
        return this.formularioLiderazgoRepository.findAll();
    }

    @Override
    @Transactional
    public FormularioLiderazgo agregar(FormularioLiderazgo pojo) {
        return this.formularioLiderazgoRepository.insert(pojo);
    }

    @Override
    @Transactional
    public FormularioLiderazgo actualizar(FormularioLiderazgo pojo) {
        return this.formularioLiderazgoRepository.save(pojo);
    }

    @Override
    @Transactional
    public FormularioLiderazgo buscaPorId(String identificador) {
        return this.formularioLiderazgoRepository.findById(identificador).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) {
     FormularioLiderazgo formLide = this.buscaPorId(identificador);
     if (formLide != null){
         this.formularioLiderazgoRepository.delete(formLide);
     }
    }

    @Override
    @Transactional
    public FormularioLiderazgo buscarFormularioPorUsuario(String usuario) {
        return this.formularioLiderazgoRepository.findByUser(usuario);
    }


}
