package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Termino;
import edu.uce.seguridad.repository.TerminoRepository;
import edu.uce.seguridad.service.service.TerminoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TerminoServiceImpl implements TerminoService {

    private TerminoRepository terminoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Termino> buscarTodos() throws NoEncontradoExcepcion {
        List<Termino> list = this.terminoRepository.findAll();
        if(list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No se han encontrado términos");
        }
        return list;
    }

    @Override
    @Transactional
    public Termino agregar(Termino pojo) throws DataAccessException {
        return this.terminoRepository.insert(pojo);
    }

    @Override
    @Transactional
    public Termino actualizar(Termino pojo) throws DataAccessException {
        return this.terminoRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public Termino buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Optional<Termino> terminoOptional = this.terminoRepository.findByNombre(identificador);
        if(terminoOptional.isPresent()) {
            return terminoOptional.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No existe el término ".concat(identificador));
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        this.terminoRepository.findByNombre(identificador).ifPresent(this.terminoRepository::delete);
    }
}
