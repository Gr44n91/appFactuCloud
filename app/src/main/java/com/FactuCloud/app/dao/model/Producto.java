package com.FactuCloud.app.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productoID;

    private String nombre;
    private Double precio;
    private Integer stock;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleFactura> detallesFactura;
}
