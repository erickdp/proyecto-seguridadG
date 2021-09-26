package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.HojaDeRevisionDeGerencia;
import edu.uce.seguridad.model.RevisionContinua;
import edu.uce.seguridad.repository.RevisionContinuaRepo;
import edu.uce.seguridad.service.service.RevisionContinuaService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JsonDataSource;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RevisionContinuaServiceImpl implements RevisionContinuaService {

    private RevisionContinuaRepo repository;
    private EstadoCompletadoServiceImpl estadoCompletadoService;

    @Override
    public List<RevisionContinua> buscarTodos() throws NoEncontradoExcepcion {
        return this.repository.findAll();
    }

    @Override
    public RevisionContinua agregar(RevisionContinua pojo) throws DataAccessException {
        return repository.insert(pojo);
    }

    @Override
    public RevisionContinua actualizar(RevisionContinua pojo) throws DataAccessException {
        RevisionContinua aux = this.repository.save(pojo);
        estadoCompletadoService.verificarEstadoPaso10(pojo.getUsuario());
        return aux;
    }

    @Override
    public RevisionContinua buscaPorId(String identificador) throws NoEncontradoExcepcion {
        return this.repository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) throws EliminacionException {
        RevisionContinua revision = this.buscaPorId(identificador);
        if (revision != null) {
            this.repository.delete(revision);
            estadoCompletadoService.verificarEstadoPaso10(revision.getUsuario());
        }
    }

    @Override
    public RevisionContinua buscarRevisionPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        return this.repository.findRevisionByUsuario(nombreUsuario);
    }

    @Override
    public void eliminarRespuestaFormularioRevisionContinua(String nombreUsuario) {
        RevisionContinua revision = this.repository.findRevisionByUsuario(nombreUsuario);
        if (revision != null) {
            this.repository.delete(revision);
            estadoCompletadoService.verificarEstadoPaso10(revision.getUsuario());
        }
    }

    @Override
    public byte[] generarPdfEnBytes(String user) throws IOException, JRException {
        RevisionContinua data = this.repository.findRevisionByUsuario(user);
        InputStream resource = new ClassPathResource("resumen.jrxml").getInputStream();

        List<JSONObject> dataJson = new ArrayList<>();
        if (data != null) {

            data.getTemas().forEach(var -> {
                JSONObject aux = new JSONObject();
                aux.put("paso", var.getPaso());
                aux.put("temaRevVer", var.getTemaRevVer());
                aux.put("formulario", var.getFormulario());
                aux.put("estado", var.getEstado());
                aux.put("cambios", var.getCambios());
                aux.put("temasRevisar", var.getTemasRevisar());

                dataJson.add(aux);
            });

            ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(dataJson.toString().getBytes());
            JsonDataSource ds = new JsonDataSource(jsonDataStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            Map<String, Object> map = new HashMap<>();
            map.put("createdBy", "sgcn");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, ds);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
        return new byte[0];
    }
}
