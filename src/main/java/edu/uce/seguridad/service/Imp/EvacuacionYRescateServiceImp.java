package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EvacuacionYRescate;
import edu.uce.seguridad.repository.EvacuacionYRescateRepository;
import edu.uce.seguridad.service.service.EvacuacionYRescateService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class EvacuacionYRescateServiceImp implements EvacuacionYRescateService {

    private EvacuacionYRescateRepository evacuacionYRescateRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EvacuacionYRescate> buscarTodos() throws NoEncontradoExcepcion {
        List<EvacuacionYRescate> list = this.evacuacionYRescateRepository.findAll();
        if (list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No existe registros");
        }
        return list;
    }

    @Override
    @Transactional
    public EvacuacionYRescate agregar(EvacuacionYRescate pojo) throws DataAccessException {
        return this.evacuacionYRescateRepository.save(pojo);
    }

    @Override
    @Transactional
    public EvacuacionYRescate actualizar(EvacuacionYRescate pojo) throws DataAccessException {
        return this.evacuacionYRescateRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public EvacuacionYRescate buscaPorId(String identificador) throws NoEncontradoExcepcion {
        throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros para : ".concat(identificador));
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        List<EvacuacionYRescate> eyrPojo = this.evacuacionYRescateRepository.findByUsuario(identificador);
        eyrPojo.forEach(this.evacuacionYRescateRepository::delete);
    }

    private static final Map<String, String> RECURSOS_DEFAULT() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("departamentoFabrica", "");
        stringStringHashMap.put("lugarEvacuacion", "");
        stringStringHashMap.put("liderPlanEvacuacion", "");
        stringStringHashMap.put("encargadoRescate", "");
        stringStringHashMap.put("datosHospital", "");
        stringStringHashMap.put("datosBomberos", "");
        return stringStringHashMap;
    }

    @Override
    @Transactional(readOnly = true)
    public EvacuacionYRescate buscarPorUsuarioYRiesgo(String usuario, String riesgo) {
        Optional<EvacuacionYRescate> usuarioAndRiesgo = this.evacuacionYRescateRepository.findByUsuarioAndRiesgo(usuario, riesgo);
        if (usuarioAndRiesgo.isPresent()) {
            return usuarioAndRiesgo.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No se ha encontrado registros de :".concat(usuario).concat(" para ").concat(riesgo));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<EvacuacionYRescate> buscarPorUsuario(String usuario) throws NoEncontradoExcepcion {
        List<EvacuacionYRescate> lista = this.evacuacionYRescateRepository.findByUsuario(usuario);
        if (lista.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros para :".concat(usuario));
        }
        return lista;
    }

    @Override
    @Transactional
    public void iniciarEvacuacionYRescate(String riesgoIngresado, String usuario) {
        Optional<EvacuacionYRescate> evacuacionYRescateOptional = this.evacuacionYRescateRepository.findByUsuarioAndRiesgo(usuario, riesgoIngresado);
        evacuacionYRescateOptional.ifPresent(this.evacuacionYRescateRepository::delete); //Como actualiza o agrega un form RIP el anterior de este para evitar problemas se elimina y se forma uno nuevo
        EvacuacionYRescate evacuacionYRescate = new EvacuacionYRescate();
        evacuacionYRescate.setRiesgo(riesgoIngresado);
        evacuacionYRescate.setUsuario(usuario);
        evacuacionYRescate.setRecursos(RECURSOS_DEFAULT());
        this.evacuacionYRescateRepository.save(evacuacionYRescate);
    }
}
