package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.RevisionContinua;
import net.sf.jasperreports.engine.JRException;
import org.json.JSONObject;

import java.io.IOException;

public interface RevisionContinuaService extends BaseService<RevisionContinua, String> {
    RevisionContinua buscarRevisionPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;
    void eliminarRespuestaFormularioRevisionContinua(String nombreUsuario);
    byte[] generarPdfEnBytes(String user) throws IOException, JRException;
}
