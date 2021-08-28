package edu.uce.seguridad.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormularioPlanDePrueba {

    @Id
    private String _id;

    private String user;
    private String tipoDeEjercicio;
    private String objetivo;
    private String grupoAProbar;

//    private Date diaDelEjercicio;
//    private Date revisionPostEjercicio;
// el tipo date me da error a la hora de hacer modificaion
// y tambien a la hora de buscar por eso van tipo string
    private String diaDelEjercicio;
    private String revisionPostEjercicio;
}
