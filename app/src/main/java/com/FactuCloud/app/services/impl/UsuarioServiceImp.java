package com.FactuCloud.app.services.impl;

import com.FactuCloud.app.dao.model.Cliente;
import com.FactuCloud.app.dao.model.Usuario;
import com.FactuCloud.app.dao.repository.IUsuarioRespository;
import com.FactuCloud.app.dto.response.ClienteRs;
import com.FactuCloud.app.dto.request.usuario.UsuarioRq;
import com.FactuCloud.app.dto.response.UsuarioRs;
import com.FactuCloud.app.exceptions.ResourceNotFoundException;
import com.FactuCloud.app.services.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsuarioServiceImp implements UsuarioService {

    private final IUsuarioRespository usuarioRespository;

    public UsuarioServiceImp(IUsuarioRespository usuarioRespository) {
        this.usuarioRespository = usuarioRespository;
    }

    @Override
    public UsuarioRs crearUsuario(UsuarioRq request) {

        Usuario usuarioToSave = Usuario.builder()
                .cif(request.getCifDni().toUpperCase())
                .ciudad(request.getCiudad())
                .direccion(request.getDireccion())
                .email(request.getEmail())
                .nombre(request.getNombre())
                .pais(request.getPais())
                .telefono(request.getTelefono())
                .build();

        usuarioRespository.save(usuarioToSave);

        return this.usuarioToUsuarioRs(usuarioToSave);
    }

    @Override
    public UsuarioRs updateUsuario(UsuarioRq request) {

        Usuario usuarioFromDb = usuarioRespository.findByCifIgnoreCase(request.getCifDni()).orElseThrow(
                () -> new ResourceNotFoundException("Usuarios"));
        // Actualiza solo los campos que son diferentes en el usuario recibido y el usuario existente
        if (!request.getNombre().equals(usuarioFromDb.getNombre())) {
            usuarioFromDb.setNombre(request.getNombre());
        }
        if (!request.getEmail().equals(usuarioFromDb.getEmail())) {
            usuarioFromDb.setEmail(request.getEmail());
        }
        if (!request.getCiudad().equals(usuarioFromDb.getCiudad())) {
            usuarioFromDb.setCiudad(request.getCiudad());
        }
        if (!request.getDireccion().equals(usuarioFromDb.getDireccion())) {
            usuarioFromDb.setDireccion(request.getDireccion());
        }
        if (!request.getPais().equals(usuarioFromDb.getPais())) {
            usuarioFromDb.setPais(request.getPais());
        }
        if (!request.getTelefono().equals(usuarioFromDb.getTelefono())) {
            usuarioFromDb.setTelefono(request.getTelefono());
        }

        usuarioRespository.save(usuarioFromDb);

        return this.usuarioToUsuarioRs(usuarioFromDb);
    }

    //===============================================

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRespository.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorCif(String cif) {
        return null;
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return null;
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRespository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuarios"));

        usuarioRespository.deleteById(id);
    }

    @Override
    public List<ClienteRs> getClientesByUsuario(String cifDni) {
        Usuario usuario = usuarioRespository.findByCifIgnoreCase(cifDni).orElseThrow(
                () -> new ResourceNotFoundException("Usuarios"));
        List<Cliente> clientes = usuario.getClientes();
        List<ClienteRs> response = new ArrayList();
        if(!clientes.isEmpty()) {
            //Mapeamos a ClienteRs
            for (Cliente c : clientes) {
                response.add(this.clienteToClienteRs(c));
            }
        }
        return response;
    }

    @Override
    public ClienteRs getClienteByCifDni(Integer usuarioId,String cifDni) {
        Usuario usuario = usuarioRespository.findByCifIgnoreCase(cifDni).orElseThrow(
                () -> new ResourceNotFoundException("Usuarios"));

        Cliente cliente = usuario.getClientes().stream()
                .filter((c) -> c.getCifDni().equalsIgnoreCase(cifDni))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Usuarios"));

        return this.clienteToClienteRs(cliente);
    }

    //============UTILES==================

    private UsuarioRs usuarioToUsuarioRs(Usuario usuario) {
        UsuarioRs response = UsuarioRs.builder()
                .nombre(usuario.getNombre())
                .cif(usuario.getCif())
                .direccion(usuario.getDireccion())
                .ciudad(usuario.getCiudad())
                .pais(usuario.getPais())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .build();

        return response;
    }

    private ClienteRs clienteToClienteRs(Cliente cliente) {
        ClienteRs response = ClienteRs.builder()
                .nombre(cliente.getNombre())
                .cifDni(cliente.getCifDni())
                .direccion(cliente.getDireccion())
                .ciudad(cliente.getCiudad())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .build();
        return  response;
    }
}
