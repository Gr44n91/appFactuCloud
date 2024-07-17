package com.FactuCloud.app.dao.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UsuarioId;

    private String nombre;
    @Column(unique = true)
    private String cif;
    private String direccion;
    private String ciudad;
    private String pais;
    @Column(unique = true)
    private String email;
    private String telefono;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Usuario(String nombre, String cif, String direccion, String ciudad, String pais, String email, String telefono) {
        this.nombre = nombre;
        this.cif = cif;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.email = email;
        this.telefono = telefono;
    }
}
