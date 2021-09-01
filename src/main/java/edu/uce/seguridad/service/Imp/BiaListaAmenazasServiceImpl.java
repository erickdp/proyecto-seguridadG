package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BiaListaAmenazas;
import edu.uce.seguridad.model.BiaValoracionGeneral;
import edu.uce.seguridad.model.DataAmenazas;
import edu.uce.seguridad.model.DataRecursos;
import edu.uce.seguridad.repository.BiaListaAmenazasRepository;
import edu.uce.seguridad.service.service.BiaListaAmenazasService;
import edu.uce.seguridad.service.service.BiaValoracionGeneralService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static edu.uce.seguridad.util.Utileria.agregarAmenazasGeneral;
import static edu.uce.seguridad.util.Utileria.recursosDefault;

@Service
@AllArgsConstructor
public class BiaListaAmenazasServiceImpl implements BiaListaAmenazasService {

    private BiaListaAmenazasRepository biaListaAmenazasRepository;

    private BiaValoracionGeneralService valoracionGeneralService;

    @Override
    @Transactional(readOnly = true)
    public List<BiaListaAmenazas> buscarTodos() throws NoEncontradoExcepcion {
        List<BiaListaAmenazas> lista = this.biaListaAmenazasRepository.findAll();
        if (lista.isEmpty()) {
            throw new NoEncontradoExcepcion("respuesta", "No se encuentran datos");
        }
        return lista;
    }

    @Override
    @Transactional
    public BiaListaAmenazas agregar(BiaListaAmenazas pojo) throws DataAccessException {
        // Lógica para crear el siguiente 6.4 BiaValoracionGeneral
        this.valoracionGeneralService.agregar(BiaValoracionGeneral.builder()
                .usuario(pojo.getUsuario())
                .amenazas(agregarAmenazasGeneral(pojo))
                .recursos(recursosDefault())
                .build());
        return this.biaListaAmenazasRepository.insert(pojo);
    }

    @Override
    @Transactional
    public BiaListaAmenazas actualizar(BiaListaAmenazas pojo) throws DataAccessException {
        this.buscaPorId(pojo.get_id());
        // Lógica para Actualizar el siguiente 6.4 BiaValoracionGeneral
        BiaValoracionGeneral biaValoracionGene = this.valoracionGeneralService.buscarPorUsuario(pojo.getUsuario());

        this.valoracionGeneralService.actualizar(BiaValoracionGeneral.builder()
                ._id(biaValoracionGene.get_id())
                .usuario(pojo.getUsuario())
                .amenazas(agregarAmenazasGeneral(pojo))
                .recursos(recursosDefault())
                .build());
        return this.biaListaAmenazasRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public BiaListaAmenazas buscaPorId(String identificador) throws NoEncontradoExcepcion {
        BiaListaAmenazas biaListaAmenazas = this.biaListaAmenazasRepository.findById(identificador).orElse(null);
        if (biaListaAmenazas == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se encuentran datos");
        }
        return biaListaAmenazas;
    }

    @Override
    @Transactional
    public void eliminarDocumento(String identificador) throws EliminacionException {
        BiaListaAmenazas data = this.buscaPorId(identificador);
        this.biaListaAmenazasRepository.delete(data);
    }

    @Override
    @Transactional(readOnly = true)
    public BiaListaAmenazas buscarPorUsuario(String usuario) {
        BiaListaAmenazas data = this.biaListaAmenazasRepository.findByUsuario(usuario);
        if (data == null) {
            throw new NoEncontradoExcepcion("respuesta", "No se encuentran datos");
        }
        return data;
    }
}
