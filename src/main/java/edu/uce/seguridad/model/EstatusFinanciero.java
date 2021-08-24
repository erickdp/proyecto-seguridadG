package edu.uce.seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class EstatusFinanciero {

    private String _id;
    private String usuario;
    private double fondosDisponiblesA;
    private double costoRecuperacionB;
    private double gastosOrdinariosC;
    private double balanceABC;
}
