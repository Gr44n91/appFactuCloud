package com.FactuCloud.app.services;

import com.FactuCloud.app.dao.model.Usuario;
import com.FactuCloud.app.dto.response.ClienteRs;
import com.FactuCloud.app.dto.request.usuario.UsuarioRq;
import com.FactuCloud.app.dto.response.UsuarioRs;

import java.util.List;

public interface UsuarioService {

    //CRUD SERVICES
    UsuarioRs crearUsuario(UsuarioRq request);
    UsuarioRs updateUsuario(UsuarioRq request);
    void deleteUsuario(Integer id);
    List<ClienteRs> getClientesByUsuario(String cifDni);

    //Metodos para el administrador
    List<Usuario> obtenerTodosUsuarios();
    Usuario obtenerUsuarioPorCif(String cif);
    Usuario obtenerUsuarioPorId(Integer id);
    ClienteRs getClienteByCifDni(Integer usuarioId, String cifDni);

}
