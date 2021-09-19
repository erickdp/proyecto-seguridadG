package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAAnalisiImpactoNegocio;
import edu.uce.seguridad.model.ProteccionYMitigacion;
import edu.uce.seguridad.model.RecursosMitigar;
import edu.uce.seguridad.repository.BIAAnalisiImpactoNegocioRepository;
import edu.uce.seguridad.repository.ProteccionYMitigacionRepository;
import edu.uce.seguridad.service.service.BIAAnalisiImpactoNegocioService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BIAAnalisiImpactoNegocioServiceImp implements BIAAnalisiImpactoNegocioService {

    private BIAAnalisiImpactoNegocioRepository negocioRepository;

    private ProteccionYMitigacionRepository proteccionYMitigacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BIAAnalisiImpactoNegocio> buscarTodos() throws NoEncontradoExcepcion {
        List<BIAAnalisiImpactoNegocio> list = this.negocioRepository.findAll();
        if (list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No existen registros");
        }
        return list;
    }

    @Override
    @Transactional
    public BIAAnalisiImpactoNegocio agregar(BIAAnalisiImpactoNegocio pojo) throws DataAccessException { // Sirve para agregar o actualizar
        List<ProteccionYMitigacion> proteccionYMitigacions = this.proteccionYMitigacionRepository.findByUsuario(pojo.getUsuario());// Solo obtengo 1 por departamento, pero se recicla por eso es lista
        if (!proteccionYMitigacions.isEmpty()) {
            this.proteccionYMitigacionRepository.deleteAll(proteccionYMitigacions);
        }

        List<RecursosMitigar> mitigarArrayList = new ArrayList<>();

        pojo.getDetalle().forEach(procesos -> {
            if (procesos.isProcesoCritico()) { // Se toman los proceso criticos para iniciar la mitigacion
                RecursosMitigar recursosMitigar = new RecursosMitigar();
                recursosMitigar.setRecurso(procesos.getNombreProceso()); // El recurso es el nombre del proceso
                recursosMitigar.setObjetivo("");
                recursosMitigar.setAccion("");
                recursosMitigar.setPlan("");
                recursosMitigar.setPlazoEstablecidoMax("DEFAULT");
                mitigarArrayList.add(recursosMitigar);
            }
        });

        ProteccionYMitigacion proteccionYMitigacion = new ProteccionYMitigacion();
        proteccionYMitigacion.setUsuario(pojo.getUsuario());
        proteccionYMitigacion.setRecursosMitigar(mitigarArrayList);

        this.proteccionYMitigacionRepository.save(proteccionYMitigacion); // las preguntas se deben de actualizar en el propio controlador

        return this.negocioRepository.save(pojo);
    }

    @Override
    @Transactional
    public BIAAnalisiImpactoNegocio actualizar(BIAAnalisiImpactoNegocio pojo) throws DataAccessException {
        return this.negocioRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public BIAAnalisiImpactoNegocio buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Optional<BIAAnalisiImpactoNegocio> biaAnalisiImpactoNegocio = this.negocioRepository.findByUsuario(identificador);
        if (biaAnalisiImpactoNegocio.isPresent()) {
            return biaAnalisiImpactoNegocio.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros para ".concat(identificador));
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        Optional<BIAAnalisiImpactoNegocio> negocioRepositoryByUsuario = this.negocioRepository.findByUsuario(identificador);
        negocioRepositoryByUsuario.ifPresent(this.negocioRepository::delete);
    }

    @Override
    public void eliminarRespuestaBiaAnalisisNegocio(String nombreUsuario) {
        Optional<BIAAnalisiImpactoNegocio> negocioRepositoryByUsuario = this.negocioRepository.findByUsuario(nombreUsuario);
        negocioRepositoryByUsuario.ifPresent(this.negocioRepository::delete);
    }
}
