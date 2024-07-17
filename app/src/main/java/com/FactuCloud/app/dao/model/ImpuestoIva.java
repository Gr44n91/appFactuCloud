package com.FactuCloud.app.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "impuestoiva")
@NoArgsConstructor
public class ImpuestoIva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Double porcentaje;
}
