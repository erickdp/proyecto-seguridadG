package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Bia2;
import edu.uce.seguridad.repository.Bia2Repository;
import edu.uce.seguridad.service.service.Bia2Service;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class Bia2ServiceImpl implements Bia2Service {

    Bia2Repository bia2Repository;

    @Override
    @Transactional(readOnly = true)
    public List<Bia2> buscarTodos() throws NoEncontradoExcepcion {
        List<Bia2> lista = this.bia2Repository.findAll();
        if (lista.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se ha encontrado registros");
        }
        return lista;
    }

    @Override
    @Transactional
    public Bia2 agregar(Bia2 pojo) throws DataAccessException {
        return this.bia2Repository.insert(pojo);
    }

    @Override
    @Transactional
    public Bia2 actualizar(Bia2 pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        return this.bia2Repository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public Bia2 buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Bia2 bia22 = this.bia2Repository.findById(identificador).orElse(null);
        if (bia22 == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se encontro el registro con el id: ".concat(identificador));
        }
        return bia22;
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        Bia2 bia2s = this.buscaPorId(identificador);
        if (bia2s == null) {
            throw new NoEncontradoExcepcion("Respuesta", "No se han encontrado");
        }
        this.bia2Repository.delete(bia2s);
    }

    @Override
    public List<Bia2> buscarporUsuario(String usuario) {
       List<Bia2> Bia = this.bia2Repository.findBia2ByUsuario(usuario);
       if (Bia.isEmpty()){
           throw new NoEncontradoExcepcion("Respuesta","No se encontro el formulario para el usuario". concat(usuario));
       }
       return Bia;
    }

    @Override
    public void eliminarporUsuario(String usuario) {
        List<Bia2> bia2List = buscarporUsuario(usuario);
        if (!bia2List.isEmpty()){
            bia2List.forEach(lista -> this.eliminarDocumento(lista.get_id()));
        }

    }
}
