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
public class HojaDeRevisionDeGerencia {

    @Id
    private String _id;

    private String user;
    private String asuntoARevisaryVerificar;
    private String PersonaACargo;

// el tipo date me da error a la hora de hacer modificaion
// y tambien a la hora de buscar por eso van tipo string
// private Date fechaLimite;
    private String fechaLimite;
    private String altaGerencia;
}
