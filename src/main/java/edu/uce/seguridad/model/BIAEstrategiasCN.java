package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BIAEstrategiasCN {
    @Id
    private String _id;
    private String usuario;
    private List<EstrategiaCN> estrategias;
}
