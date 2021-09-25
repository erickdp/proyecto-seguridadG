package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.controller.BIAControlesAlineadosALosImpactosController;
import edu.uce.seguridad.model.EstatusFinanciero;
import edu.uce.seguridad.model.EstimacionDano;
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

    private final FormularioAlcanceRepository formularioAlcanceRepository;
    private final FormularioLiderazgoRepository formularioLiderazgoRepository;
    private final FormularioImpactoRepository formularioImpactoRepository;
    private final PeriodoTolerableRepository periodoTolerableRepository;
    private final ActividadesPrioritariasRepository actividadesPrioritariasRepository;
    private final RecursoRepository recursoRepository;
    private final FormularioRIPRepository formularioRIPRepository;
    private final EstimacionDanoRepository estimacionDanoRepository;
    private final ProteccionYMitigacionRepository proteccionYMitigacionRepository;
    private final EvacuacionYRescateRepository evacuacionYRescateRepository;
    private final CentroOperacionRepository centroOperacionRepository;
    private final ContactoEmergenciaRepo contactoEmergenciaRepo;
    private final ListaContactoExternoRepository listaContactoExternoRepository;
    private final FormularioListadeAcopioRepository formularioListadeAcopioRepository;
    private final EvaluacionDanoRepository evaluacionDanoRepository;
    private final EstrategiaRepository estrategiaRepository;
    private final EstrategiasContinuidadRepository estrategiasContinuidadRepository;
    private final FondosDisponiblesRepository fondosDisponiblesRepository;
    private final FormularioCostosRecupRepository formularioCostosRecupRepository;
    private final GastosCorrientesRepository gastosCorrientesRepository;
    private final EstatusFinancieroRepository financieroRepository;
    private final FormularioMedidasFinancierasRepository formularioMedidasFinancierasRepository;
    private final FormularioPlanDePruebaRepository formularioPlanDePruebaRepository;
    private final HojaDeRevisionDeGerenciaRepository hojaDeRevisionDeGerenciaRepository;
    private final RevisionContinuaRepo revisionContinuaRepo;

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

        if (!estimacionDanoRepository.findByUsuario(user).isEmpty()) {
            List<EstimacionDano> name = estimacionDanoRepository.findByUsuario(user);
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
        int contadorMax = 0;
        double balance = 0;
        int paso = 8;
        String respuesta = "No completado";
        this.verificarExistencia(user);

        EstatusFinanciero ef = this.financieroRepository.findByUsuario(user);
        if (ef != null) {
            balance = ef.getBalanceABC(); // evito null pointer
        }

        contadorMax = (balance > 0) ? 4 : 5; // >= porque deben haber fondos y no quedarse en cero

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
        if (balance < 0) {
            if (!formularioMedidasFinancierasRepository.findByUserOrderByMedidasFinancieras(user).isEmpty()) {
                contador++;
            }
        }

        if (contador > 0) {
            respuesta = "En curso";
        }
        if (contador == contadorMax) { // CAMBIAR DEPENDIENDO EL NUMERO DE FORMULARIOS
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
        String respuesta = "No completado";

        this.verificarExistencia(user);

        if (!hojaDeRevisionDeGerenciaRepository.findByUserOrderByAsuntoARevisaryVerificar(user).isEmpty()) {
            contador++;
        }
        if (revisionContinuaRepo.findRevisionByUsuario(user) != null) {
            contador++;
        }

        if (contador == 2) {
            contador = 0;
            RevisionContinua rev = this.revisionContinuaRepo.findRevisionByUsuario(user);
            List<FormularioRevision> lista = rev.getTemas();
            for (FormularioRevision var : lista) {
                if (var.getEstado().equals("Completado")) {
                    contador++;
                }
            }
        }
        if (contador > 0) {
            respuesta = "En curso";
        }

        if (contador == 9) {
            respuesta = "Completado";
        }

        guardar(respuesta, user, paso);
    }

    public void guardar(String respuesta, String user, int paso) {
        RevisionContinua rev = this.revisionContinuaRepo.findRevisionByUsuario(user);
        List<FormularioRevision> formRev = rev.getTemas();
        formRev.get(paso - 1).setEstado(respuesta);
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
