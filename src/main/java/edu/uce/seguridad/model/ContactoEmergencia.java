package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactoEmergencia {
    @Id
    private String _id;
    private String departamento;
    private String nombrePersonal;
    private String telefono;
    private String correo;
    private String status;
}
