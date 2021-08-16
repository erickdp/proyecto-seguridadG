package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ResumenDeEstrategias;
import edu.uce.seguridad.repository.EstrategiaRepository;
import edu.uce.seguridad.service.service.EstrategiaService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class EstrategiaServiceImpl implements EstrategiaService {

    EstrategiaRepository estrategiaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResumenDeEstrategias> buscarTodos() throws NoEncontradoExcepcion {
        List<ResumenDeEstrategias> listas = this.estrategiaRepository.findAll();
        if (listas.isEmpty()){
            throw new NoEncontradoExcepcion("respuesta", "No se encuentran datos");
        }
        return listas;
    }

    @Override
    @Transactional
    public ResumenDeEstrategias agregar(ResumenDeEstrategias pojo) throws DataAccessException {
       return this.estrategiaRepository.insert(pojo);
    }

    @Override
    @Transactional
    public ResumenDeEstrategias actualizar(ResumenDeEstrategias pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.estrategiaRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public ResumenDeEstrategias buscaPorId(String identificador) throws NoEncontradoExcepcion {
        ResumenDeEstrategias resumenDeEstrategias = this.estrategiaRepository.findById(identificador).orElse(null);
        if (resumenDeEstrategias == null){
            throw new NoEncontradoExcepcion("respuesta" ,"No se encontro registro para el id: ".concat(identificador));
        }
        return resumenDeEstrategias;
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
    ResumenDeEstrategias resumenDeEstrategias = this.buscaPorId(identificador);
    if (resumenDeEstrategias == null){
        throw new NoEncontradoExcepcion("respuesta","No se encuentra el registro a eliminar");
    }
    this.estrategiaRepository.delete(resumenDeEstrategias);
    }

    @Override
    public List<ResumenDeEstrategias> buscarporUsuario(String usuario) {
        List<ResumenDeEstrategias> resumenDeEstrategias = this.estrategiaRepository.findByUsuario(usuario);
        if (resumenDeEstrategias.isEmpty()){
            throw new NoEncontradoExcepcion("resumen", "No se encontro el registro para el usuario:" .concat(usuario));

        }
        return  resumenDeEstrategias;
    }
}
