package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Estimacion;
import edu.uce.seguridad.model.EstimacionDano;
import edu.uce.seguridad.model.FormularioRIP;
import edu.uce.seguridad.model.Recurso;
import edu.uce.seguridad.repository.EstimacionDanoRepository;
import edu.uce.seguridad.repository.FormularioRIPRepository;
import edu.uce.seguridad.repository.RecursoRepository;
import edu.uce.seguridad.service.service.RecursoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static edu.uce.seguridad.util.Utileria.establecerEstimaciones;

@Service
@AllArgsConstructor
public class RecursoServiceImpl implements RecursoService {

    private RecursoRepository repository;

    private FormularioRIPRepository formularioRIPRepository;

    private EstimacionDanoRepository estimacionDanoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Recurso> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public Recurso agregar(Recurso pojo) throws DataAccessException {


        // guardar / actualizar para el formulario 4.1

        // EstimacionDano debe actualizarse no crarse 


        return repository.insert(pojo);
    }

    @Override
    @Transactional
    public Recurso actualizar(Recurso pojo) throws DataAccessException { // Si agrega nuevos recursos, el form 4.2 deben de formatearse y agregar los nuevos recursos
        List<FormularioRIP> formRip = this.formularioRIPRepository.findByUser(pojo.getUsuario());
        if (!formRip.isEmpty()) {
            List<EstimacionDano> estimacionesDano = this.estimacionDanoRepository.findByUsuario(pojo.getUsuario());
            if (!estimacionesDano.isEmpty()) {
                this.estimacionDanoRepository.deleteAll(estimacionesDano);
            }
            Recurso recursoUpdate = pojo;
            formRip.forEach(rip -> {
                EstimacionDano estimacionDano = new EstimacionDano();
                estimacionDano.setRiesgo(rip.getNombreRiesgo());
                estimacionDano.setImpacto(rip.getImpacto());
                estimacionDano.setProbabilidad(rip.getProbabilidad());
                estimacionDano.setUsuario(rip.getUser());

                Map<String, List<Estimacion>> recursosEstimar = establecerEstimaciones(recursoUpdate);
                estimacionDano.setRecursosNecesarios(recursosEstimar);

                this.estimacionDanoRepository.save(estimacionDano);
            });
        }
        return this.repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public Recurso buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) {
        Recurso recurso = this.buscarRecursoPorUsuario(identificador);
        if (recurso != null) {
            this.repository.delete(recurso);
        }
    }

//    @Override
//    public List<Recurso> buscarRecursosPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
//        return this.repository.findRecursosByUsuario(nombreUsuario);
//    }

    @Override
    @Transactional(readOnly = true)
    public Recurso buscarRecursoPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        /* si lanzamos una exception del tipo NoEcontradoException no permitirá crear un formulario RIP
          debido a que no se termina de ejecutar el método agregar(), las excepciones están controladas
          en ese método. PERO para otros método se puede necesitar que lance la excepcion
       */
        return this.repository.findByUsuario(nombreUsuario);
    }
}
