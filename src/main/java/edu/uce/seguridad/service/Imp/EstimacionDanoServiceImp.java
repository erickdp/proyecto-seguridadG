package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.*;
import edu.uce.seguridad.repository.EstimacionDanoRepository;
import edu.uce.seguridad.repository.ProteccionYMitigacionRepository;
import edu.uce.seguridad.service.service.EstimacionDanoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EstimacionDanoServiceImp implements EstimacionDanoService {

    private EstimacionDanoRepository estimacionDanoRepository;

    private ProteccionYMitigacionRepository proteccionYMitigacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EstimacionDano> buscarTodos() throws NoEncontradoExcepcion {
        List<EstimacionDano> list = this.estimacionDanoRepository.findAll();
        if (list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No existen registros");
        }
        return list;
    }

    @Override
    @Transactional
    public EstimacionDano agregar(EstimacionDano pojo) throws DataAccessException {
        return this.estimacionDanoRepository.insert(pojo);
    }

    @Override
    @Transactional
    public EstimacionDano actualizar(EstimacionDano pojo) throws DataAccessException {
        Optional<ProteccionYMitigacion> mitigacionOptional = this.proteccionYMitigacionRepository.findByUsuarioAndRiesgo(pojo.getUsuario(), pojo.getRiesgo());
        if (mitigacionOptional.isPresent()) { // Si ya existia el form 5.1 lo borro para actualizar con la estimacion de dano nueva
            this.proteccionYMitigacionRepository.delete(mitigacionOptional.get());
        }

//       Creo un form 5.1 a partir de los nuevos datos que envie el usuario del 4.2
        ProteccionYMitigacion proteccionYMitigacion = new ProteccionYMitigacion();
        proteccionYMitigacion.setUsuario(pojo.getUsuario());
        proteccionYMitigacion.setRiesgo(pojo.getRiesgo());
        List<RecursosMitigar> listaCompelta = new ArrayList<>();

        pojo.getRecursosNecesarios().forEach((llave, valor) -> { // Itero los recursos necesarios para filtrar aquellos que necesitan medidas de mitigacion

            // Aquellos que necesiten una mitigacion los paso a una lista que se filtra por este parametro
            List<Estimacion> recursosAMitigar = valor.stream().filter(necesitaMitigacion -> necesitaMitigacion.isMedidas() == true).collect(Collectors.toList());

            listaCompelta.addAll(recursosAMitigar.stream().map(recurso -> { // De la lista filtrada obtengo cada uno de los recursos para pasarlo a una nueva lista
                RecursosMitigar recursosMitigar = new RecursosMitigar();
                recursosMitigar.setRecurso(recurso.getRecurso());
                recursosMitigar.setAccion("");
                recursosMitigar.setPlan("");
                recursosMitigar.setObjetivo("");
                recursosMitigar.setPlazoEstablecidoMax(Arrays.asList("xx","",""));
                recursosMitigar.setDepartamentoEncargado("");
                return recursosMitigar;
            }).collect(Collectors.toList()));

        });

        proteccionYMitigacion.setRecursosMitigar(listaCompelta); // Agrego al forma de proteccion y mitigacion
        this.proteccionYMitigacionRepository.save(proteccionYMitigacion); // A la par que actualizo el 4.2 creo un 5.1

        return this.estimacionDanoRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public EstimacionDano buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Optional<EstimacionDano> estimacionesDano = this.estimacionDanoRepository.findById(identificador);
        if (estimacionesDano.isPresent()) {
            return estimacionesDano.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros para: ".concat(identificador));
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        List<EstimacionDano> formularioEstimacion = this.estimacionDanoRepository.findByUsuario(identificador); // Como se eliminan varios forms devuelve una lista
        if (!formularioEstimacion.isEmpty()) {
            formularioEstimacion.forEach(form -> this.estimacionDanoRepository.deleteById(form.get_id()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EstimacionDano buscarFormularioPorUsuarioYRiesgo(String usuario, String riesgo) throws NoEncontradoExcepcion {
        Optional<EstimacionDano> formulario = this.estimacionDanoRepository.findByUsuarioAndRiesgo(usuario, riesgo);
        if (formulario.isPresent()) {
            return formulario.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No se ha encontrado el formulario de: "
                .concat(riesgo).concat(" para: ").concat(usuario));
    }
}
