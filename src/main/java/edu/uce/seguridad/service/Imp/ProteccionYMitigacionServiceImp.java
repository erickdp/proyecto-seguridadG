package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ProteccionYMitigacion;
import edu.uce.seguridad.repository.ProteccionYMitigacionRepository;
import edu.uce.seguridad.service.service.ProteccionYMitigacionService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProteccionYMitigacionServiceImp implements ProteccionYMitigacionService {

    private ProteccionYMitigacionRepository proteccionYMitigacionRepository;

    private EstadoCompletadoServiceImpl estadoCompletadoService;

    @Override
    @Transactional(readOnly = true)
    public List<ProteccionYMitigacion> buscarTodos() throws NoEncontradoExcepcion {
        List<ProteccionYMitigacion> list = this.proteccionYMitigacionRepository.findAll();
        if (list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No existen registros");
        }
        return list;
    }

    @Override
    @Transactional
    public ProteccionYMitigacion agregar(ProteccionYMitigacion pojo) throws DataAccessException {
        return this.proteccionYMitigacionRepository.save(pojo);
    }

    @Override
    @Transactional
    public ProteccionYMitigacion actualizar(ProteccionYMitigacion pojo) throws DataAccessException {
        ProteccionYMitigacion aux = this.proteccionYMitigacionRepository.save(pojo);
        estadoCompletadoService.verificarEstadoPaso5(pojo.getUsuario());
        return aux;
    }

    @Override
    @Transactional(readOnly = true)
    public ProteccionYMitigacion buscaPorId(String identificador) throws NoEncontradoExcepcion { // Reemplazado por obtenerPorFormPorRiesgoYUsuario
        throw new NoEncontradoExcepcion("mensaje", "No existen registros para: ".concat(identificador));
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        List<ProteccionYMitigacion> formPM = this.proteccionYMitigacionRepository.findByUsuario(identificador);
        this.proteccionYMitigacionRepository.deleteAll(formPM);
        estadoCompletadoService.verificarEstadoPaso5(formPM.get(0).getUsuario()); // poible error por index desbordado
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<ProteccionYMitigacion> obtenerPorUsuario(String usuario) throws NoEncontradoExcepcion {
        List<ProteccionYMitigacion> mitigacionOptional = this.proteccionYMitigacionRepository.findByUsuario(usuario);
        if(mitigacionOptional.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros para : ".concat(usuario));
        }
        return mitigacionOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public ProteccionYMitigacion obtenerPorFormPorRiesgoYUsuario(String riesgo, String usuario) throws NoEncontradoExcepcion {
        Optional<ProteccionYMitigacion> mitigacionOptional = this.proteccionYMitigacionRepository.findByUsuarioAndRiesgo(usuario, riesgo);
        if (mitigacionOptional.isPresent()) {
            return mitigacionOptional.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros para : ".concat(riesgo).concat(" de ").concat(usuario));
    }


}
