package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioMedidasFinancieras;
import edu.uce.seguridad.model.GastosCorrientes;
import edu.uce.seguridad.repository.FormularioMedidasFinancierasRepository;
import edu.uce.seguridad.service.service.FormularioMedidasFinancierasService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FormularioMedidasFinancierasServiceImp implements FormularioMedidasFinancierasService {

    private FormularioMedidasFinancierasRepository repository;
    private EstadoCompletadoServiceImpl estadoCompletadoService;
    
    @Override
    @Transactional(readOnly = true)
    public List<FormularioMedidasFinancieras> buscarPorUserFiltrarPorMedidasFinancieras(String user) throws NoEncontradoExcepcion {
        List<FormularioMedidasFinancieras> contactos = this.repository.findByUserOrderByMedidasFinancieras(user);
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(user));
        }
        return contactos;
    }

    @Override
    @Transactional
    public void eliminarPorUsuarioFinanciero(String nombreUsuario) {
        List<FormularioMedidasFinancieras> form= this.repository.findByUserOrderByMedidasFinancieras(nombreUsuario);
        if (!form.isEmpty()) {
            form.forEach(contacto -> this.eliminarDocumento(contacto.getUser()));
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<FormularioMedidasFinancieras> buscarTodos() throws NoEncontradoExcepcion {
        List<FormularioMedidasFinancieras> contactos = this.repository.findAll();
        if (contactos.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros");
        }
        return contactos;
    }

    @Override
    @Transactional
    public FormularioMedidasFinancieras agregar(FormularioMedidasFinancieras pojo) throws DataAccessException {
        FormularioMedidasFinancieras aux = this.repository.insert(pojo);
        estadoCompletadoService.verificarEstadoPaso8(pojo.getUser());
        return aux;
    }

    @Override
    @Transactional
    public FormularioMedidasFinancieras actualizar(FormularioMedidasFinancieras pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public FormularioMedidasFinancieras buscaPorId(String identificador) throws NoEncontradoExcepcion {
        FormularioMedidasFinancieras contactos = this.repository.findById(identificador).orElse(null);
        if (contactos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return contactos;
    }

    // Opcional poner un estado para no eliminarlo (no relacional)
    // Que se elimine con todo o le temes al exito?? XDD
    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws NoEncontradoExcepcion  {
        FormularioMedidasFinancieras contatos = this.buscaPorId(identificador); 
        if (contatos == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        this.repository.delete(contatos);
        estadoCompletadoService.verificarEstadoPaso8(contatos.getUser());
   }
    
     // By Leo robado de Erick es para que cuando elimine el usuario todos sus registros igual se eliminen
    @Override
    @Transactional
    public void eliminarPorMedidasFinancieras(String user) {
        List<FormularioMedidasFinancieras> contactos = this.repository.findByUserOrderByMedidasFinancieras(user);
        if (!contactos.isEmpty()) {
            contactos.forEach(contacto -> this.eliminarDocumento(contacto.get_id()));
            estadoCompletadoService.verificarEstadoPaso8(contactos.get(0).getUser()); // posible error
        }
    }
}
