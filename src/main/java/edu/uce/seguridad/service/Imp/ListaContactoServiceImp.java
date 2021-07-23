package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.model.ListaContacto;
import edu.uce.seguridad.repository.ListaContactoRepository;
import edu.uce.seguridad.service.service.ListaContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListaContactoServiceImp implements ListaContactoService {

    @Autowired
    private ListaContactoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ListaContacto> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public ListaContacto agregar(ListaContacto pojo) {
        return this.repository.insert(pojo);
    }

    @Override
    @Transactional
    public ListaContacto actualizar(ListaContacto pojo) {
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public ListaContacto buscaPorId(String identificador) {
        return this.repository.findById(identificador).orElse(null);
    }

    // Opcional poner un estado para no eliminarlo (no relacional)
    @Override
    public void eliminarDocumento(String identificador) {
        ListaContacto contacto = this.buscaPorId(identificador);
        if (contacto != null) {
            this.repository.delete(contacto);
        }
    }
}
