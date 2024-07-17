package com.FactuCloud.app.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detellesimpuestos")
@Data
@NoArgsConstructor
public class DetalleImpuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detalleImpuestoID;

    @ManyToOne
    @JoinColumn(name = "facturaID")
    private Factura factura;

    private String tipoImpuesto;
    private Double monto;

}
