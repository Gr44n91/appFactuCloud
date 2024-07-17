package com.FactuCloud.app.dao.repository;

import com.FactuCloud.app.dao.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Integer> {

    @Query("SELECT MAX(f.numeroFactura) FROM Factura f WHERE f.usuario.id = :usuarioId AND f.ejercicio = :anio")
    String findUltimoNumeroFacturaByUsuarioIdAndEjercicio(@Param("usuarioId") Integer usuarioId, @Param("anio") Integer anio);




}
