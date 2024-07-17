package com.FactuCloud.app.dao.repository;

import com.FactuCloud.app.dao.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
}
