package edu.uce.seguridad.controller;

import edu.uce.seguridad.model.EstimacionDano;
import edu.uce.seguridad.service.service.EstimacionDanoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sgcnegocio/estimaciondano")
@AllArgsConstructor
public class EstimacionDanoController {

//    private EstimacionDano estimacionDano;

    private EstimacionDanoService estimacionDanoService;

    /*
    ENDPOINT ACTIVO devuelve todos los formularios de estimacion de riesgo
    - Si hay formularios se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningun registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping
    public ResponseEntity<?> getEstimacionDano() {
        List<EstimacionDano> estimacionDanos = this.estimacionDanoService.buscarTodos();
        return new ResponseEntity<List<EstimacionDano>>(estimacionDanos, HttpStatus.OK);
    }

    /*
    ENDPOINT que busca el formulario por nombre de usuario y el riesgo a evaluar (debe empezar por el prioritario), el nombre de usuario se debe de enviar
    por la URL y el resigo tambien.
    - Si se encuentra el formulario del usuario con ese riesgo se devuelve un estado 200 - Ok
    - En el caso de no encontrar ningun registro se devuelve un estado 404 - NOT FOUND
    - En el caso de fallar la BD se devuelve un estado 500 - INTERNAL SERVER ERROR
    * */
    @GetMapping("/{usuario}/{riesgo}")
    public ResponseEntity<?> getEstimacionDanoByUser(@PathVariable("usuario") String usuario,
                                                     @PathVariable("riesgo") String riesgo) {
        EstimacionDano estimacionDano = this.estimacionDanoService.buscarFormularioPorUsuarioYRiesgo(usuario, riesgo);
        return new ResponseEntity<EstimacionDano>(estimacionDano, HttpStatus.OK);
    }

    /*
    ENDPOINT ACTIVO, ACTUALIZA EL FORMULARIO DE EESTIMACION DE DANOS PARA UN USUARIO, SE DEBE ENVIAR UN JSON ASI:
    "_id": "6110a19f5879ca74841706c0",
    "usuario": "tsegud31",
    "riesgo": "SISMO",
    "probabilidad": "A",
    "impacto": "A",
    "recursosNecesarios": {
        "Servicios Sociales Escenciales": [
            {
                "tipo": "Electricidad",
                "dano": 1,
                "dias": 32,
                "medidasNecesarias": true
            },
            {
                "tipo": "Transito",
                "dano": 2,
                "dias": 9,
                "medidasNecesarias": false
            }
        ],
        "Socios de Negocios": [
            {
                "tipo": "Socios",
                "dano": 1,
                "dias": 8,
                "medidasNecesarias": true
            },
            {
                "tipo": "Proveedores",
                "dano": 2,
                "dias": 5,
                "medidasNecesarias": false
            }
        ],
        "Recursos Internos": [
            {
                "tipo": "Inmuebles",
                "dano": 3,
                "dias": 7,
                "medidasNecesarias": true
            },
            {
                "tipo": "Equipos",
                "dano": 3,
                "dias": 7,
                "medidasNecesarias": true
            }
        ]
    }
    SI LA TRANSACCION TIENE EXITO DEVUELVE ESTADO 200 - OK
    SI LA TRANSACCION FALLA DEVUELVE INTERNAL SERVER ERROR - 500
    * */
    @PutMapping("/actualizarDanos")
    public ResponseEntity<Void> actualizarDanos(
            @RequestBody EstimacionDano estimacionDano
    ) {
        this.estimacionDanoService.actualizar(estimacionDano);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /*
    ENDPOINT INACTIVO
    Solo se lo agrega debio a que no seria tan necesario eliminar el documento ya que implica
    otros documentos, en el caso de eliminar a la persona se elimina todos sus documentos
    asociados
    * */
    @DeleteMapping("/{usuario}")
    public ResponseEntity<Void> eliminarDocumento(
            @PathVariable("usuario") String usuario
    ) {
        this.estimacionDanoService.eliminarDocumento(usuario);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
