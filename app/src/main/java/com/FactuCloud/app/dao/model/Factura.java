package com.FactuCloud.app.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facturaID;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    private Date fecha;
    private Double subtotal;
    private Double total;
    private String numeroFactura; // Nuevo atributo numeroFactura añadido
    private Integer ejercicio;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<DetalleFactura> detallesFactura;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<DetalleImpuesto> detallesImpuestos;
}
