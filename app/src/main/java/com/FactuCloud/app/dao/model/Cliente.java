package com.FactuCloud.app.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    private String nombre;
    @Column(name = "cif_dni", unique = true) // Esto hace que el campo CIF_DNI sea único en la base de datos
    private String cifDni;
    private String direccion;
    private String ciudad;
    private String pais;
    @Column(unique = true)
    private String email;
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsuarioId")
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;
}
