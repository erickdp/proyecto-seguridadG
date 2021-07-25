package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormularioLiderazgo {
    @Id
    private String _id;
    private String user;
    private String personal;
    private String negocio;
}
