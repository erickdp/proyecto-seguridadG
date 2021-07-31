package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.ListaContacto;
import edu.uce.seguridad.service.service.ListaContactoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/listaContacto")
@AllArgsConstructor
public class ListaContactoController {

    private ListaContactoService listaContactoService;

    /*
    ENDPOINT que busca todos los contactos de un usuario, el id se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/listaContacto/buscarContactosPorUser/60fcb37db3c0630156388256

    * La lista ya se encuentra ordenada por el campo tipoContacto

    - Si se encuentra al menos un contacto guardado devuelve un estado 302 - FOUND
    - Si no encuentra registros con respecto al usuario devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    */
    @GetMapping("/buscarContactosPorUser/{user}")
    public ResponseEntity<?> buscarContactosporUser(@PathVariable(value = "user") String user) {
        List<ListaContacto> contactos = this.listaContactoService.buscarPorUserFiltrarPorTipoContacto(user);
        return new ResponseEntity<>(contactos, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe enviar un JSON con user, tipoContacto, nombre, telefono, correo, servicio
    * tipoContacto: se debe definir en el front valores unicos para este campo (comboBox)
    Ej:
     {
        "user": "60fcb37db3c0630156388256",
        "tipoContacto": "proveedor de servicios",
        "nombre": "Netlife",
        "telefono": "3920000",
        "correo": "info@netlife.net.ec",
        "servicio": "Internet"
    }
    */
    @PostMapping("/agregarContacto")
    public ResponseEntity<?> agregarContacto(@RequestBody ListaContacto contacto) {
        ListaContacto contactoNew = this.listaContactoService.agregar(contacto);
        return new ResponseEntity<>(contactoNew, HttpStatus.CREATED);
    }

    /*
    ENDPOINT activo
    Se debe mandar el id por la URL y los nuevos datos en formato JSON del contacto que se quiere actualizar
    Ej:
    http://localhost:8080/sgcnegocio/listaContacto/actualizarContacto/60fd8fedf46fcb59784d3cdd
    {
        "user": "60fcb37db3c0630156388256",
        "tipoContacto": "proveedor de servicios",
        "nombre": "Netlife Update",
        "telefono": "3920000 Update",
        "correo": "info@netlife.net.ec",
        "servicio": "Internet y telefonía"
    }
    */
    @PutMapping("/actualizarContacto/{id}")
    public ResponseEntity<?> actualizarContacto(@RequestBody ListaContacto contacto, @PathVariable(value = "id") String id) {
        contacto.set_id(id);
        ListaContacto contactoActualizado = this.listaContactoService.actualizar(contacto);
        return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
    }

    /*
    ENDPOINT activo
    Se debe mandar unicamente el id en el URL para eliminar un contacto
    Ej:
    http://localhost:8080/sgcnegocio/listaContacto/eliminarContacto/60fd8fedf46fcb59784d3cdd
    */
    @DeleteMapping("/eliminarContacto/{id}")
    public ResponseEntity<?> eliminarContacto(@PathVariable(value = "id") String id) {
        this.listaContactoService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
