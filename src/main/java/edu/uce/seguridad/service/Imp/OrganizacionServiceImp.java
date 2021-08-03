package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.MiClaseException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Organizacion;
import edu.uce.seguridad.repository.OrganizacionRepository;
import edu.uce.seguridad.service.service.OrganizacionService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizacionServiceImp implements OrganizacionService {

    private OrganizacionRepository organizacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Organizacion> buscarTodos() {
        return this.organizacionRepository.findAll();
    }

    @Override
    @Transactional
    public Organizacion agregar(Organizacion pojo) throws DataAccessException {
        pojo.setOrganizacion(pojo.getOrganizacion().trim().toUpperCase());

        List<String> departamentosN = new ArrayList<>();

        pojo.getDepartamentos().forEach(dep -> {
            departamentosN.add(dep.trim().toUpperCase()); //Itero para eliminar espacios y pasarlos a mayusculas
        });

        pojo.setDepartamentos(departamentosN);

        pojo.setContacto(pojo.getContacto().trim()); // Elminar espacios en el caso de haberlo

        return this.organizacionRepository.insert(pojo);
    }

    // El actualizar se reemplaza por actualizarOrganizacion
    @Override
    @Transactional
    public Organizacion actualizar(Organizacion pojo) throws DataAccessException {
        if (this.buscaPorId(pojo.get_id()) == null) {
            throw new MiClaseException("No se ha encontrado la organización. Verifique los datos");
        }
        pojo.setOrganizacion(pojo.getOrganizacion().trim().toUpperCase());

        List<String> departamentosN = new ArrayList<>();

        pojo.getDepartamentos().forEach(dep -> departamentosN.add(dep.trim().toUpperCase()));

        pojo.setDepartamentos(departamentosN);

        pojo.setContacto(pojo.getContacto().trim());
        return this.organizacionRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public Organizacion buscaPorId(String identificador) throws NoEncontradoExcepcion {
        Optional<Organizacion> organizacion = this.organizacionRepository.findById(identificador);
        if (organizacion.isPresent()) {
            return organizacion.get();
        }
        throw new NoEncontradoExcepcion(
                "respuesta", "No se han encontrado registros de: ".concat(identificador));
    }

    @Override
    @Deprecated
    public void eliminarDocumento(String identificador) {
        //
    }

    @Override
    @Transactional
    public void eliminarPorNombreOrganizacion(String nombreOrganizacion) throws NoEncontradoExcepcion {
        Organizacion organizacion = this.buscarPorNombreOrganizacion(nombreOrganizacion);
        if (organizacion == null) {
            throw new NoEncontradoExcepcion("No se ha encontrado la organización: ".concat(nombreOrganizacion));
        }
        this.organizacionRepository.deleteById(organizacion.get_id());
    }

    @Override
    @Transactional(readOnly = true)
    public Organizacion buscarPorNombreOrganizacion(String nombreOrganizacion) throws NoEncontradoExcepcion {
        Optional<Organizacion> organizacion = this.organizacionRepository.findOrganizacionByOrganizacion(nombreOrganizacion);
        if (organizacion.isPresent()) {
            return organizacion.get();
        }
        throw new NoEncontradoExcepcion(
                "respuesta", "No se han encontrado registros de: ".concat(nombreOrganizacion));
    }
}
