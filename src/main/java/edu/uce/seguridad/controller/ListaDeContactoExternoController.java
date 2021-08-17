package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.ListaContactoExterno;
import edu.uce.seguridad.service.service.ListaContactoExternoService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/sgcnegocio/listaContactoExterno")
@AllArgsConstructor
public class ListaDeContactoExternoController {
    private ListaContactoExternoService listaContactoExternoService;

    /*
    ENDPOINT que busca todos los contactos de un usuario, el id del user se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/listaContacto/buscarContactosPorUser/vsaavedrai30

    * La lista ya se encuentra ordenada por el campo tipoContacto

    - Si se encuentra al menos un contacto guardado devuelve un estado 302 - FOUND
    - Si no encuentra registros con respecto al usuario devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    */
    @GetMapping("/buscarContactosPorUser/{user}")
    public ResponseEntity<?> buscarContactosporUser(@PathVariable(value = "user") String user) {
        List<ListaContactoExterno> contactos = this.listaContactoExternoService.buscarPorUserFiltrarPorTipoContacto(user);
        return new ResponseEntity<>(contactos, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe enviar un JSON con user, tipoContacto, nombre, telefono, correo, servicio
    * tipoContacto: se debe definir en el front valores unicos para este campo (comboBox)
    Ej:
     {
        "user": "vsaavedrai30",
        "tipoContacto": "proveedor de servicios",
        "nombre": "Netlife",
        "telefono": "3920000",
        "correo": "info@netlife.net.ec",
        "servicio": "Internet"
    }
    */
    @PostMapping("/agregarContacto")
    public ResponseEntity<?> agregarContacto(@RequestBody ListaContactoExterno contacto) {
        ListaContactoExterno contactoNew = this.listaContactoExternoService.agregar(contacto);
        return new ResponseEntity<>(contactoNew, HttpStatus.CREATED);
    }

    /*
    ENDPOINT activo
    Se debe mandar el id del contacto por la URL y los nuevos datos en formato JSON del contacto que se quiere actualizar
    Ej:
    http://localhost:8080/sgcnegocio/listaContacto/actualizarContacto/60fd8fedf46fcb59784d3cdd
    {
        "user": "vsaavedrai30",
        "tipoContacto": "proveedor de servicios",
        "nombre": "Netlife Update",
        "telefono": "3920000 Update",
        "correo": "info@netlife.net.ec",
        "servicio": "Internet y telefonía"
    }
    */
    @PutMapping("/actualizarContacto/{id}")
    public ResponseEntity<?> actualizarContacto(@RequestBody ListaContactoExterno contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        ListaContactoExterno contactoActualizado = this.listaContactoExternoService.actualizar(contacto);
        return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe mandar unicamente el id del contacto en el URL para eliminar un contacto
    Ej:
    http://localhost:8080/sgcnegocio/listaContacto/eliminarContacto/60fd8fedf46fcb59784d3cdd
    */
    @DeleteMapping("/eliminarContacto/{id}")
    public ResponseEntity<?> eliminarContacto(@PathVariable(value = "id") String id) {
        this.listaContactoExternoService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
