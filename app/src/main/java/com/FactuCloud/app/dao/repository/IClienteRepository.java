package com.FactuCloud.app.dao.repository;

import com.FactuCloud.app.dao.model.Cliente;
import com.FactuCloud.app.dao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.cifDni) = LOWER(:cif)")
    Optional<Cliente> findByCifIgnoreCase(String cif);
    Optional<Cliente> findByCifDni(String cifDni);

    @Query(value = "SELECT * FROM Clientes c " +
            "WHERE (LOWER(c.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.cif_dni) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND c.UsuarioId = :usuarioId",
            nativeQuery = true)
    Optional<Cliente> buscarPorNombreCifDniEmailYUsuarioIdIgnoreCase(
            @Param("keyword") String keyword,
            @Param("usuarioId") Integer usuarioId);



    @Query("SELECT c FROM Cliente c WHERE LOWER(c.usuario.cif) = LOWER(:cifUsuario)")
    List<Cliente> findAllByCifUsuarioIgnoreCase(@Param("cifUsuario") String cifUsuario);
}
