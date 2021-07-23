package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.model.ListaEvaluacion;
import edu.uce.seguridad.repository.ListaEvaluacionRepository;
import edu.uce.seguridad.service.service.ListaEvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListaEvaluacionServiceImp implements ListaEvaluacionService {

    @Autowired
    private ListaEvaluacionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ListaEvaluacion> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public ListaEvaluacion agregar(ListaEvaluacion pojo) {
        return this.repository.insert(pojo);
    }

    @Override
    @Transactional
    public ListaEvaluacion actualizar(ListaEvaluacion pojo) {
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public ListaEvaluacion buscaPorId(String identificador) {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) {
        ListaEvaluacion listaEvaluacion = this.buscaPorId(identificador);
        if (listaEvaluacion != null) {
            this.repository.delete(listaEvaluacion);
        }
    }
}
