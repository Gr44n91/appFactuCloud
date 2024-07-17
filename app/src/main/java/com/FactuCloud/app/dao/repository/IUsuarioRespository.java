package com.FactuCloud.app.dao.repository;

import com.FactuCloud.app.dao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRespository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCif(String cif);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.cif) = LOWER(:cif)")
    Optional<Usuario> findByCifIgnoreCase(String cif);
}
