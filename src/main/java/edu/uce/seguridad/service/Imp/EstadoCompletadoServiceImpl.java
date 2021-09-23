package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.model.FormularioRevision;
import edu.uce.seguridad.model.RevisionContinua;
import edu.uce.seguridad.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.uce.seguridad.util.Utileria.formulariosDefault;

@Service
@AllArgsConstructor
public class EstadoCompletadoServiceImpl {

    private FormularioAlcanceRepository formularioAlcanceRepository;
    private FormularioLiderazgoRepository formularioLiderazgoRepository;

    private FormularioImpactoRepository formularioImpactoRepository;
    private PeriodoTolerableRepository periodoTolerableRepository;
    private ActividadesPrioritariasRepository actividadesPrioritariasRepository;
    private RecursoRepository recursoRepository;
    private FormularioRIPRepository formularioRIPRepository;
    private EstimacionDanoRepository estimacionDanoRepository;
    private ProteccionYMitigacionRepository proteccionYMitigacionRepository;
    private EvacuacionYRescateRepository evacuacionYRescateRepository;
    private CentroOperacionRepository centroOperacionRepository;
    private ContactoEmergenciaRepo contactoEmergenciaRepo;
    private ListaContactoExternoRepository listaContactoExternoRepository;
    private FormularioListadeAcopioRepository formularioListadeAcopioRepository;
    private EvaluacionDanoRepository evaluacionDanoRepository;
    private EstrategiaRepository estrategiaRepository;
    private EstrategiasContinuidadRepository estrategiasContinuidadRepository;
    private FondosDisponiblesRepository fondosDisponiblesRepository;
    private FormularioCostosRecupRepository formularioCostosRecupRepository;
    private GastosCorrientesRepository gastosCorrientesRepository;
    private EstatusFinancieroRepository financieroRepository;
    private FormularioMedidasFinancierasRepository formularioMedidasFinancierasRepository;
    private FormularioPlanDePruebaRepository formularioPlanDePruebaRepository;
    private HojaDeRevisionDeGerenciaRepository hojaDeRevisionDeGerenciaRepository;
    private RevisionContinuaRepo revisionContinuaRepo;


    public void verificarEstadoPaso1(String user) {
        int contador = 0;
        int paso = 1;
        String respuesta = "No completado";

        this.verificarExistencia(user);

        if (formularioAlcanceRepository.findFormularioAlcanceByUser(user) != null) {
            contador++;
        }
        if (formularioLiderazgoRepository.findFormularioLiderazgoByUser(user) != null) {
            contador++;
        }
        if (contador > 0) {
            respuesta = "En curso";
        }
        if (contador == 2) {
            respuesta = "Completado";
        }
        guardar(respuesta, user, paso);
        verificarEstadoPaso10(user);
    }

    public void verificarEstadoPaso2(String user) {
        int contador = 0;
        int paso = 2;
        String respuesta = "No completado";
        this.verificarExistencia(user);

        if (!formularioImpactoRepository.findFormularioComparativoImpactoByUser(user).isEmpty()) {
            contador++;
        }
        if (!periodoTolerableRepository.findPeriodosByUsuario(user).isEmpty()) {
            contador++;
        }
        if (!actividadesPrioritariasRepository.findActividadesByUsuario(user).isEmpty()) {
            contador++;
        }

        if (contador > 0) {
            respuesta = "En curso";
        }
        if (contador == 3) { // CAMBIAR DEPENDIENDO EL NUMERO DE FORMULARIOS
            respuesta = "Completado";
        }
        guardar(respuesta, user, paso);
        verificarEstadoPaso10(user);
    }

    public void verificarEstadoPaso3(String user) {
        int contador = 0;
        int paso = 3;
        String respuesta = "No completado";
        this.verificarExistencia(user);

        if (recursoRepository.findByUsuario(user) != null) {
            contador++;
        }
        if (contador == 1) { // CAMBIAR DEPENDIENDO EL NUMERO DE FORMULARIOS
            respuesta = "Completado";
        }
        guardar(respuesta, user, paso);
        verificarEstadoPaso10(user);
    }

    public void verificarEstadoPaso4(String user) {
        int contador = 0;
        int paso = 4;
        String respuesta = "No completado";
        this.verificarExistencia(user);

        if (formularioRIPRepository.findByUser(user) != null) {
            contador++;
        }

        if (estimacionDanoRepository.findByUsuario(user) != null) {
            contador++;
        }

        if (contador > 0) {
            respuesta = "En curso";
        }
        if (contador == 2) { // CAMBIAR DEPENDIENDO EL NUMERO DE FORMULARIOS
            respuesta = "Completado";
        }
        guardar(respuesta, user, paso);
        verificarEstadoPaso10(user);
    }

    public void verificarEstadoPaso5(String user) {
        int contador = 0;
        int paso = 5;
        String respuesta = "No completado";
        this.verificarExistencia(user);

        if (!proteccionYMitigacionRepository.findByUsuario(user).isEmpty()) {
            contador++;
        }
        if (contador == 1) { // CAMBIAR DEPENDIENDO EL NUMERO DE FORMULARIOS
            respuesta = "Completado";
        }
        guardar(respuesta, user, paso);
        verificarEstadoPaso10(user);
    }

    public void verificarEstadoPaso6(String user) {
        int contador = 0;
        int paso = 6;
        String respuesta = "No completado";
        this.verificarExistencia(user);

        if (!evacuacionYRescateRepository.findByUsuario(user).isEmpty()) {
            contador++;
        }
        if (centroOperacionRepository.findCentroByUsuario(user) != null) {
            contador++;
        }
        if (!contactoEmergenciaRepo.findContactosByUsuario(user).isEmpty()) {
            contador++;
        }
        if (!listaContactoExternoRepository.findByUserOrderByTipoContactoAsc(user).isEmpty()) {
            contador++;
        }
        if (!formularioListadeAcopioRepository.findByUsuario(user).isEmpty()) {
            contador++;
        }
        if (!evaluacionDanoRepository.findByUsuario(user).isEmpty()) {
            contador++;
        }

        if (contador > 0) {
            respuesta = "En curso";
        }
        if (contador == 6) { // CAMBIAR DEPENDIENDO EL NUMERO DE FORMULARIOS
            respuesta = "Completado";
        }
        guardar(respuesta, user, paso);
        verificarEstadoPaso10(user);
    }

    public void verificarEstadoPaso7(String user) {
        int contador = 0;
        int paso = 7;
        String respuesta = "No completado";
        this.verificarExistencia(user);

        if (estrategiaRepository.findByUsuario(user).isPresent()) {
            contador++;
        }
        if (!estrategiasContinuidadRepository.findByUsuario(user).isEmpty()) {
            contador++;
        }
        if (contador > 0) {
            respuesta = "En curso";
        }
        if (contador == 2) { // CAMBIAR DEPENDIENDO EL NUMERO DE FORMULARIOS
            respuesta = "Completado";
        }
        guardar(respuesta, user, paso);
        verificarEstadoPaso10(user);
    }

    public void verificarEstadoPaso8(String user) {
        int contador = 0;
        int paso = 8;
        String respuesta = "No completado";
        this.verificarExistencia(user);

        if (fondosDisponiblesRepository.findByUsaurio(user).isPresent()) {
            contador++;
        }
        if (formularioCostosRecupRepository.findByUsuario(user) != null) {
            contador++;
        }
        if (gastosCorrientesRepository.findByUser(user) != null) {
            contador++;
        }
        if (financieroRepository.findByUsuario(user) != null) {
            contador++;
        }
        if (!formularioMedidasFinancierasRepository.findByUserOrderByMedidasFinancieras(user).isEmpty()) {
            contador++;
        }

        if (contador > 0) {
            respuesta = "En curso";
        }
        if (contador == 5) { // CAMBIAR DEPENDIENDO EL NUMERO DE FORMULARIOS
            respuesta = "Completado";
        }
        guardar(respuesta, user, paso);
        verificarEstadoPaso10(user);
    }

    public void verificarEstadoPaso9(String user) {
        int contador = 0;
        int paso = 9;
        String respuesta = "No completado";
        this.verificarExistencia(user);

        if (!formularioPlanDePruebaRepository.findByUserOrderByTipoDeEjercicio(user).isEmpty()) {
            contador++;
        }
        if (contador == 1) { // CAMBIAR DEPENDIENDO EL NUMERO DE FORMULARIOS
            respuesta = "Completado";
        }
        guardar(respuesta, user, paso);
        verificarEstadoPaso10(user);
    }

    public void verificarEstadoPaso10(String user) {
        int contador = 0;
        int paso = 10;
        String respuesta = "En curso";

        this.verificarExistencia(user);

        if (!hojaDeRevisionDeGerenciaRepository.findByUserOrderByAsuntoARevisaryVerificar(user).isEmpty()) {
            contador++;
        }

        RevisionContinua rev = this.revisionContinuaRepo.findRevisionByUsuario(user);
        List<FormularioRevision> lista = rev.getTemas();
        for (FormularioRevision var : lista) {
            if (var.getEstado().equals("Completado")) {
                contador++;
            }
        }
        if (contador == 9) {
            respuesta = "Completado";
        }

        guardar(respuesta, user, paso);
    }

    public void guardar(String respuesta, String user, int paso) {
        RevisionContinua rev = this.revisionContinuaRepo.findRevisionByUsuario(user);
        List<FormularioRevision> formRev = rev.getTemas();
        formRev.get(paso-1).setEstado(respuesta);
        rev.setTemas(formRev);
        revisionContinuaRepo.save(rev);
    }

    public void verificarExistencia(String user) {
        RevisionContinua rev = this.revisionContinuaRepo.findRevisionByUsuario(user);
        if (rev == null) {
            // Se crea el formulario 10.1
            this.revisionContinuaRepo.insert(RevisionContinua.builder()
                    .usuario(user)
                    .temas(formulariosDefault())
                    .build());
        }
    }

}
