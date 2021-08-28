package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioPlanDePrueba;
import edu.uce.seguridad.repository.FormularioPlanDePruebaRepository;
import edu.uce.seguridad.service.service.FormularioPlanDePruebaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormularioPlanDePruebaServiceImp implements FormularioPlanDePruebaService{
    @Autowired
    private FormularioPlanDePruebaRepository repository;

    @Override
    @Transactional
    public FormularioPlanDePrueba agregar(FormularioPlanDePrueba pojo) throws DataAccessException {
        return this.repository.insert(pojo);
    }

    @Override
    @Transactional
    public FormularioPlanDePrueba actualizar(FormularioPlanDePrueba pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioPlanDePrueba buscaPorId(String identificador) throws NoEncontradoExcepcion {
        FormularioPlanDePrueba contactos = this.repository.findById(identificador).orElse(null);
        if (contactos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion {
        FormularioPlanDePrueba contatos = this.buscaPorId(identificador);
        if (contatos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(contatos);
    }

    @Override
    public List<FormularioPlanDePrueba> buscarPorUserFiltrarPorTipoDeEjercicio(String user) throws NoEncontradoExcepcion {
        List<FormularioPlanDePrueba> contactos = this.repository.findByUserOrderByTipoDeEjercicio(user);
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarPorTipoDeEjercicio(String user) {
        List<FormularioPlanDePrueba> contactos = this.repository.findByUserOrderByTipoDeEjercicio(user);
        if (!contactos.isEmpty()) {
            contactos.forEach(contacto -> this.eliminarDocumento(contacto.get_id()));
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<FormularioPlanDePrueba> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioPlanDePrueba> contactos = this.repository.findAll();
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return contactos;
    }
}
