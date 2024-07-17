package com.FactuCloud.app.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "detallesfactura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detalleID;

    @ManyToOne
    @JoinColumn(name = "facturaID")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "productoID")
    private Producto producto;

    private String descripcion;
    private Integer cantidad;
    private Double precioUnitario;
    private Double totalLinea;
}
