package edu.uce.seguridad.service.Imp;

import edu.uce.seguridad.exception.MiClaseException;
import edu.uce.seguridad.model.Organizacion;
import edu.uce.seguridad.repository.OrganizacionRepository;
import edu.uce.seguridad.service.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizacionServiceImp implements OrganizacionService {

    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Organizacion> buscarTodos() {
        return this.organizacionRepository.findAll();
    }

    @Override
    @Transactional
    public Organizacion agregar(Organizacion pojo) {
        pojo.setNombreOrganizacion(pojo.getNombreOrganizacion().trim());
        pojo.setNombreOrganizacion(pojo.getNombreOrganizacion().toUpperCase());

        pojo.setContacto(pojo.getContacto().trim());
        return this.organizacionRepository.insert(pojo);
    }

    @Override
    @Transactional
    public Organizacion actualizar(Organizacion pojo) {
        return this.organizacionRepository.save(pojo);
    }

    @Override
    @Transactional(readOnly = true)
    public Organizacion buscaPorId(String identificador) {
        return this.organizacionRepository.findById(identificador).orElse(null);
    }

    @Override
    public void eliminarDocumento(String identificador) {
        //
    }

    @Override
    @Transactional
    public String eliminarPorNombreOrganizacion(String nombreOrganizacion) throws MiClaseException {
        Organizacion organizacion = this.buscarPorNombreOrganizacion(nombreOrganizacion);
        if(organizacion == null) {
            throw new MiClaseException("No se ha encontrado la organización: ".concat(nombreOrganizacion));
        }
        this.organizacionRepository.deleteById(organizacion.get_id());
        return "Se ha eliminado ha ".concat(organizacion.getNombreOrganizacion()).concat(" satisfactoriamente");
    }

    @Override
    @Transactional(readOnly = true)
    public Organizacion buscarPorNombreOrganizacion(String nombreOrganizacion) {
        return this.organizacionRepository.findOrganizacionByNombreOrganizacion(nombreOrganizacion);
    }

    @Override
    public Organizacion actualizarOrganizacion(Organizacion organizacion) throws MiClaseException {
        if(this.buscaPorId(organizacion.get_id()) == null) {
            throw new MiClaseException("No se ha encontrado la organización. Verifique los datos");
        }
        organizacion.setNombreOrganizacion(organizacion.getNombreOrganizacion().trim());
        organizacion.setNombreOrganizacion(organizacion.getNombreOrganizacion().toUpperCase());

        organizacion.setContacto(organizacion.getContacto().trim());
        return this.organizacionRepository.save(organizacion);
    }
}
