package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ControlPcn;
import edu.uce.seguridad.model.FormularioMedidasFinancieras;
import edu.uce.seguridad.model.RespuestasControlPcn;
import edu.uce.seguridad.repository.ControlPcnRepository;
import edu.uce.seguridad.service.service.ControlPcnService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ControlPcnServiceImp implements ControlPcnService {

    private ControlPcnRepository controlPcnRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ControlPcn> buscarTodos() throws NoEncontradoExcepcion {
        List<ControlPcn> list = this.controlPcnRepository.findAll();
        if (list.isEmpty()) {
            throw new NoEncontradoExcepcion("mensaje", "No existen registros");
        }
        return list;
    }

    @Override
    @Transactional
    public ControlPcn agregar(ControlPcn pojo) throws DataAccessException {
        short total = 0;
        List<RespuestasControlPcn> respuestasList = pojo.getRespuestas();

        if (!respuestasList.isEmpty()) {
            for (RespuestasControlPcn respuestas :
                    respuestasList) {
                total += respuestas.getRespuesta();
            }
        }
        pojo.setPuntuacionTotal((int) total);
        return this.controlPcnRepository.save(pojo);
    }

    @Override // Delego la transaccion a otro metodo
    public ControlPcn actualizar(ControlPcn pojo) throws DataAccessException {
        return this.agregar(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public ControlPcn buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Optional<ControlPcn> controlPcn = this.controlPcnRepository.findByUsuario(identificador);
        if (controlPcn.isPresent()) {
            return controlPcn.get();
        }
        throw new NoEncontradoExcepcion("mensaje", "No se han encontrado registros para ".concat(identificador));
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        this.controlPcnRepository.findByUsuario(identificador).ifPresent(this.controlPcnRepository::delete);
    }

    @Override
    public void eliminarRespuestaFormularioAlcance(String nombreUsuario) {
        this.controlPcnRepository.findByUsuario(nombreUsuario).ifPresent(this.controlPcnRepository::delete);

    }
}
