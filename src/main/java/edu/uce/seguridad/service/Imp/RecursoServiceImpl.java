package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EstimacionDano;
import edu.uce.seguridad.model.Recurso;
import edu.uce.seguridad.repository.RecursoRepository;
import edu.uce.seguridad.service.service.RecursoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RecursoServiceImpl implements RecursoService {

    private RecursoRepository repository;

    @Override
    public List<Recurso> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public Recurso agregar(Recurso pojo) throws DataAccessException {


        // guardar / actualizar para el formulario 4.1

        // EstimacionDano debe actualizarse no crarse 


        return repository.insert(pojo);
    }

    @Override
    public Recurso actualizar(Recurso pojo) throws DataAccessException {
        return this.repository.save(pojo);
    }

    @Override
    public Recurso buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
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
    public Recurso buscarRecursoPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        /* si lanzamos una exception del tipo NoEcontradoException no permitirá crear un formulario RIP
          debido a que no se termina de ejecutar el método agregar(), las excepciones están controladas
          en ese método. PERO para otros método se puede necesitar que lance la excepcion
       */
        return this.repository.findByUsuario(nombreUsuario);
    }
}
