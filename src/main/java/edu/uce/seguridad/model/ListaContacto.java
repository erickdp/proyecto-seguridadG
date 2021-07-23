package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaContacto {

    @Id
    private String _id;
    private String user;

    private String tipoContacto;
    private String nombre;
    private String telefono;
    private String correo;
    private String servicio;

}
