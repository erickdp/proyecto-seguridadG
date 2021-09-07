package edu.uce.seguridad.controller;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.BIAEstrategiasCN;
import edu.uce.seguridad.service.service.BIAEstrategiasCNService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/estrategiascn")
@AllArgsConstructor
public class BIAEstrategiaCNController {
    private BIAEstrategiasCNService service;

    /*
    ENDPOINT que busca todas las estrategias de continuidad del negocio
    Ej:
    http://localhost:8080/sgcnegocio/estrategiascn
    - Si existen estrategias de continuidad del negocio se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping
    public ResponseEntity<List<BIAEstrategiasCN>> listar() {
        List<BIAEstrategiasCN> estrategiasCN = service.buscarTodos();
        return new ResponseEntity<List<BIAEstrategiasCN>>(estrategiasCN, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca la estrategia de continuidad del negocio por un identificador, el id se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/estrategiascn/buscarEstrategiaPorId/61133f919761987103770a49
    - Si existen estrategias de continuidad del negocio se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/buscarEstrategiaPorId/{id}")
    public ResponseEntity<BIAEstrategiasCN> listarPorId(@PathVariable("id") String idEstrategiaCN) {
        BIAEstrategiasCN estrategiaCN = service.buscaPorId(idEstrategiaCN);
        if (estrategiaCN == null) {
            throw new NoEncontradoExcepcion("respuesta", "Id no encontrado " + idEstrategiaCN);
        }
        return new ResponseEntity<BIAEstrategiasCN>(estrategiaCN, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca todas las estrategias de continuidad del negocio por usuario, el nombre de usuario se debe enviar por la URL
    Ej:
    http://localhost:8080/sgcnegocio/estrategiascn/buscarEstrategiasPorUsuario/nombreUsuario
    - Si existen estrategias de continuidad del negocio se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningún registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */

    @GetMapping("/buscarEstrategiasPorUsuario/{usuario}")
    public ResponseEntity<List<BIAEstrategiasCN>> buscarPorUsuario(@PathVariable("usuario") String nombreUsuario) {
        List<BIAEstrategiasCN> estrategiasCNS = service.buscarEstrategiasPorUsuario(nombreUsuario);
        return new ResponseEntity<List<BIAEstrategiasCN>>(estrategiasCNS, HttpStatus.OK);
    }

    /*
    ENDPOINT para guardar una estrategia de continuidad del negocio
    Se debe enviar un JSON con el nombre del usuario y una lista con las estrategias de continuidad del negocio
    Ej:
    http://localhost:8080/sgcnegocio/estrategiascn
    JSON
    {
        "usuario": "Usuario",
        "estrategias": [
            {
                "estrategia" : "Reanudar operaciones en la localidad afectada",
                "prioridad" : "Muy alta",
                "actividadesPrimordiales" : ["Activación de Equipos", "Continuidad de Servicios"],
                "recursosPrimordiales" : ["Servidores", "Equipos", "Infraestructura"],
                "proveedoresExternos" : ["Energía eléctrica", "Internet"]
            },
            {
                "estrategia" : "Reanudar operaciones en offsite",
                "prioridad" : "Alta",
                "actividadesPrimordiales" : ["Levantar el protocolo", "Activar tree call", "Reasignación de servicios"],
                "recursosPrimordiales" : ["Servidores", "Equipos", "Infraestructura", "Backups"],
                "proveedoresExternos" : ["Energía eléctrica", "Internet", "Telecomunicaciones", "HelpDesk"]
            },
            {
                "estrategia" : "Continuar operaciones con métodos alternativos",
                "prioridad" : "Media",
                "actividadesPrimordiales" : ["Levantar el protocolo", "Activar tree call"],
                "recursosPrimordiales" : ["Backups"],
                "proveedoresExternos" : ["HelpDesk", "Infraestructura"]
            }
        ]
    }
    */
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> guardar(@RequestBody BIAEstrategiasCN estrategiasCN) {
        BIAEstrategiasCN estrategia = service.agregar(estrategiasCN);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(estrategia.get_id()).toUri();
        return ResponseEntity.created(location).build();
    }

    /*
    ENDPOINT para actualizar una estrategia de continuidad del negocio
    Se debe mandar el id por la URL y los nuevos datos en formato JSON de la estrategia que se quiere actualizar
    Ej:
    http://localhost:8080/sgcnegocio/estrategiascn/61133f919761987103770a49
    JSON
    {
        "usuario": "Usuario",
        "estrategias": [
            {
                "estrategia" : "Reanudar operaciones en la localidad afectada",
                "prioridad" : "Muy alta",
                "actividadesPrimordiales" : ["Activación de Equipos", "Continuidad de Servicios"],
                "recursosPrimordiales" : ["Servidores", "Equipos", "Infraestructura"],
                "proveedoresExternos" : ["Energía eléctrica", "Internet"]
            },
            {
                "estrategia" : "Reanudar operaciones en offsite",
                "prioridad" : "Alta",
                "actividadesPrimordiales" : ["Levantar el protocolo", "Activar tree call", "Reasignación de servicios"],
                "recursosPrimordiales" : ["Servidores", "Equipos", "Infraestructura", "Backups"],
                "proveedoresExternos" : ["Energía eléctrica", "Internet", "Telecomunicaciones", "HelpDesk"]
            },
            {
                "estrategia" : "Continuar operaciones con metodos alternativos",
                "prioridad" : "Media",
                "actividadesPrimordiales" : ["Levantar el protocolo", "Activar tree call"],
                "recursosPrimordiales" : ["Backups"],
                "proveedoresExternos" : ["HelpDesk", "Infraestructura"]
            }
        ]
    }
    */
    @PutMapping("/{id}")
    public ResponseEntity<Object> modificar(@RequestBody BIAEstrategiasCN estrategiaCN, @PathVariable(value = "id") String id) {
        estrategiaCN.set_id(id);
        BIAEstrategiasCN estrategiaUpdated = service.actualizar(estrategiaCN);
        return new ResponseEntity<Object>(estrategiaUpdated, HttpStatus.OK);
    }

    /*
    ENDPOINT
    Se debe mandar únicamente el id en el URL cuando se va a eliminar una estrategia de continuidad del negocio
    Ej:
    http://localhost:8080/sgcnegocio/estrategiascn/61133f919761987103770a49
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPorId(@PathVariable("id") String id) {
        service.eliminarDocumento(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
