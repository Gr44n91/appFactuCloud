package com.FactuCloud.app.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "impuestoirpf")
@NoArgsConstructor
@Entity
public class ImpuestoIrpf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Double porcentaje;
}
