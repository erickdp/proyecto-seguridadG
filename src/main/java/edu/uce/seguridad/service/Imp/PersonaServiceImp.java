package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioRIP;
import edu.uce.seguridad.model.Persona;
import edu.uce.seguridad.model.Usuario;
import edu.uce.seguridad.repository.PersonaRepository;
import edu.uce.seguridad.service.service.*;
import edu.uce.seguridad.util.Utileria;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonaServiceImp implements PersonaService {

    private PersonaRepository personaRepository;
    private FormularioAlcanceService formularioAlcanceService;
    private FormularioLiderazgoService formularioLiderazgoService;
    private ListaContactoService listaContactoService;
    private ListaEvaluacionService listaEvaluacionService;
    private FormularioRIPService formularioRIPService;
    private EstimacionDanoService estimacionDanoService;
    private RecursoService recursoService;
    private ProteccionYMitigacionService proteccionYMitigacionService;
    private EvacuacionYRescateService evacuacionYRescateService;
    private CentroOperacionService centroOperacionService;
    private ContactoEmergenciaService contactoEmergenciaService;
    private ListaContactoExternoService listaContactoExternoService;
    private FormularioListadeAcopioService formularioListadeAcopioService;
    private FormularioEvaluacionDanosIIService formularioEvaluacionDanosIIService;
    private EstrategiasContinuidadService estrategiasContinuidadService;
    private EstrategiaService estrategiaService;
    private FondosDisponiblesService fondosDisponiblesService;
    private FormularioCostosRecupService formularioCostosRecupService;
    private GastosCorrienteService gastosCorrienteService;
    private FormularioMedidasFinancierasService medidasFinancierasService;
    private FormularioPlanDePruebaService formularioPlanDePruebaService;
    private RevisionContinuaService revisionContinuaService;
    private ControlPcnService controlPcnService;
    private BIAAnalisiImpactoNegocioService impactoNegocioService;
    private BIACuestionarioService biaCuestionarioService;
    private Bia2Service bia2Service;
    private BIAEstrategiasCNService biaEstrategiasCNService;
    private BiaListaAmenazasService listaAmenazasService;
    private EstatusFinancieroService estatusFinancieroService;


    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarTodos() throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findAll();
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros.");
        }
        return personas;
    }

    @Override
    @Transactional
    public Persona agregar(Persona pojo) throws DataAccessException {
        pojo.setUsuario(Utileria.generarUsuario
                (pojo.getNombre(), pojo.getApellido(), pojo.getUsuario().getRole()));
        return this.personaRepository.insert(pojo);
    }

    @Override
    @Transactional
    public Persona actualizar(Persona pojo) throws DataAccessException {
        Usuario nuevoUsuario = Utileria.generarUsuario(pojo.getNombre(), pojo.getApellido(), pojo.getUsuario().getRole());
        while (this.personaRepository.findPersonaByUsuario(nuevoUsuario.getNombreUsuario()).get() != null) {
            nuevoUsuario = Utileria.generarUsuario(pojo.getNombre(), pojo.getApellido(), pojo.getUsuario().getRole());
        }
        pojo.setUsuario(nuevoUsuario);
        return this.personaRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Persona persona = this.personaRepository.findById(identificador).orElse(null);
        if (persona == null) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros de: ".concat(identificador));
        }
        return persona;
    }

    //    Se lo reemplaza por eliminarPersonaPorNombreUsuario
    @Override
    @Deprecated
    public void eliminarDocumento(String nombreUsuario) throws EliminacionException {

    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPersonaPorUsuarioYContrasena(String nombreUsuario, String contrasena) throws NoEncontradoExcepcion {
        Optional<Persona> persona = this.personaRepository.findPersonaByUsuarioYContrasena(nombreUsuario, contrasena);
        if (persona.isPresent()) {
            return persona.get();
        }
        throw new NoEncontradoExcepcion("respuesta", "Error en las credenciales ingresadas, pruebe de nuevo.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorOrganizacion(String organizacion) throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findPersonaByOrganizacion(organizacion);
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(organizacion));
        }
        return personas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorRol(String role) throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findPersonaByRole(role);
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(role));
        }
        return personas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaPorRoleYOrganizacion(String role, String organizacion) throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findPersonaByRolAndOrganization(role, organizacion);
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(role).concat(" en ").concat(organizacion));
        }
        return personas;
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPersonaPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        Optional<Persona> persona = this.personaRepository.findPersonaByUsuario(nombreUsuario);
        if (persona.isPresent()) {
            return persona.get();
        }
        throw new NoEncontradoExcepcion("respuesta", "No se han encontrado registros para: ".concat(nombreUsuario));
    }

    @Override
    @Transactional
    public void eliminarPersonaPorNombreUsuario(String nombreUsuario) throws NoEncontradoExcepcion {
        Persona persona = this.buscarPersonaPorUsuario(nombreUsuario);
        this.personaRepository.deleteById(persona.get_id());
        this.formularioAlcanceService.eliminarRespuestaFormularioAlcance(nombreUsuario); // SE ELIMINA EL FORM DE ALCANCE
        this.formularioLiderazgoService.eliminarRespuestaFormularioLiderazgo(nombreUsuario); // SE ELIMINA EL FORM DE LIDERAZGO
        this.listaContactoService.eliminarConcatosPorUser(nombreUsuario); // Lista de contactos servicio eliminado
        this.listaEvaluacionService.eliminarEvaluacionesPorUser(nombreUsuario); // Lista de evaluacion servicio eliminado
        this.recursoService.eliminarDocumento(nombreUsuario); // 3.2 LISTO
        this.formularioRIPService.eliminarPorUsusario(nombreUsuario); // 4.1 listo
        this.estimacionDanoService.eliminarDocumento(nombreUsuario); // Eliminacion por usuario del form 4.2
        this.proteccionYMitigacionService.eliminarDocumento(nombreUsuario); // Eliminacion de form 5.1
        this.evacuacionYRescateService.eliminarDocumento(nombreUsuario); // Eliminacion de fomr 6.1
        this.centroOperacionService.eliminarPorUsuario(nombreUsuario); // Eliminacion de form 6.2 listo by saul start
        this.contactoEmergenciaService.eliminarConUsuario(nombreUsuario); // Eliminacion fe form 6.3
        this.listaContactoExternoService.eliminarConcatosPorUser(nombreUsuario); // Eliminacion de form 6.4
        this.formularioListadeAcopioService.eliminarPorUsuario(nombreUsuario); // Eliminacion de form 6.5
        this.formularioEvaluacionDanosIIService.eliminarEvaluacionesPorUser(nombreUsuario); // Eliminacion de form 6.6
        this.estrategiaService.eliminarConUsuario(nombreUsuario); // Eliminacion de fomr 7.1
        this.estrategiasContinuidadService.eliminarConUsuario(nombreUsuario); // Eliminacion de fomr 7.2
        this.fondosDisponiblesService.eliminarConUsuario(nombreUsuario); // Eliminacion de form 8.1
        this.formularioCostosRecupService.eliminarConUsuario(nombreUsuario); // Eliminación de form 8.2
        this.gastosCorrienteService.eliminarPorUsuario(nombreUsuario); // Eliminacion de form 8.3 by saul end
        this.estatusFinancieroService.eliminarPorUsuario(nombreUsuario);//Eliminación de form 8.4
        this.medidasFinancierasService.eliminarPorMedidasFinancieras(nombreUsuario);//Eliminacion de form 8.5 by sam
        this.formularioPlanDePruebaService.eliminarRespuestaFormularioPlanDePruebas(nombreUsuario);//Eliminación de form 9.1 by sam
        this.revisionContinuaService.eliminarRespuestaFormularioRevisionContinua(nombreUsuario);//Eliminación de form 10.1 by sam
        this.controlPcnService.eliminarRespuestaFormularioPCN(nombreUsuario);//Eliminar form 10.2 by sam
        this.impactoNegocioService.eliminarRespuestaBiaAnalisisNegocio(nombreUsuario);//Eliminar Bia 6 by sam
        this.biaCuestionarioService.eliminarRespuestaFormularioBIAC(nombreUsuario);//Eliminar form Bia 6.1 by sam
        this.bia2Service.eliminarporUsuarioBIA(nombreUsuario);//Eliminar form 6.2 by sam
        this.biaEstrategiasCNService.eliminarRespuestaFormularioEstrategiaCN(nombreUsuario);//Eliminar form Bia 6.3 by sam
        this.listaAmenazasService.eliminarRespuestaFormularioAmenaza(nombreUsuario);//Eliminar form Bia 6.4 by sam

    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonasPorOrganizacionYDepartamento(String organizacion, String departamento) throws NoEncontradoExcepcion {
        List<Persona> personas = this.personaRepository.findPersonaByOrganizacionAndDepartamento(
                organizacion, departamento
        );
        if (personas.isEmpty()) {
            throw new NoEncontradoExcepcion(
                    "respuesta", "No se han encontrado registros para: ".concat(departamento).concat(" en ").concat(organizacion));
        }
        return personas;
    }
}
