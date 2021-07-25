package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.model.FormularioAlcance;
import edu.uce.seguridad.repository.FormularioAlcanceRepository;
import edu.uce.seguridad.service.service.FormularioAlcanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormularioAlcanceServiceImpl implements FormularioAlcanceService {

    @Autowired
    private FormularioAlcanceRepository formularioAlcanceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FormularioAlcance> buscarTodos() {
        return this.formularioAlcanceRepository.findAll();
    }

    @Override
    @Transactional
    public FormularioAlcance agregar(FormularioAlcance pojo) {
        return this.formularioAlcanceRepository.insert(pojo);
    }

    @Override
    @Transactional
    public FormularioAlcance actualizar(FormularioAlcance pojo) {

        return this.formularioAlcanceRepository.save(pojo);
    }

    @Override
    @Transactional
    public FormularioAlcance buscaPorId(String identificador) {
    return this.formularioAlcanceRepository.findById(identificador).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) {
        FormularioAlcance formu = this.buscaPorId(identificador);
        if (identificador != null){
            this.formularioAlcanceRepository.delete(formu);

        }

    }

    @Override
    @Transactional(readOnly = true)
    public FormularioAlcance buscarFormularioAlPorUsua(String usuario) {

        return this.formularioAlcanceRepository.findByUser(usuario);
    }
}
