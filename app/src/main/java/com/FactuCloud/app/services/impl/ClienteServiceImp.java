package com.FactuCloud.app.services.impl;

import com.FactuCloud.app.dao.model.Cliente;
import com.FactuCloud.app.dao.repository.IClienteRepository;
import com.FactuCloud.app.dao.repository.IUsuarioRespository;
import com.FactuCloud.app.dto.request.ClienteRq;
import com.FactuCloud.app.dto.response.ClienteRs;
import com.FactuCloud.app.exceptions.BadRequestException;
import com.FactuCloud.app.exceptions.ResourceNotFoundException;
import com.FactuCloud.app.services.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteServiceImp implements ClienteService {

    private final IClienteRepository clienteRepository;
    private final IUsuarioRespository usuarioRespository;

    public ClienteServiceImp(IClienteRepository clienteRepository, IUsuarioRespository usuarioRespository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRespository = usuarioRespository;
    }

    @Override
    public ClienteRs crearCliente(ClienteRq request) {

        Cliente clienteNuevo = new Cliente();
        clienteNuevo.setNombre(request.getNombre());
        clienteNuevo.setCifDni(request.getCifDni());
        clienteNuevo.setDireccion(request.getDireccion());
        clienteNuevo.setCiudad(request.getCiudad());
        clienteNuevo.setEmail(request.getEmail());
        clienteNuevo.setTelefono(request.getTelefono());
        clienteNuevo.setUsuario(usuarioRespository.findByCifIgnoreCase(request.getCifUsuario()).orElseThrow(
                () -> new ResourceNotFoundException("Usuarios with CIF-DNI: " + request.getCifUsuario())));

        clienteRepository.save(clienteNuevo);


        return this.clienteToClienteRs(clienteNuevo);
    }

    @Override
    public ClienteRs updateCliente(ClienteRq request) {

        Cliente clienteFromDb = clienteRepository.findByCifIgnoreCase(request.getCifDni()).orElseThrow(
                () -> new ResourceNotFoundException("Clientes with Cif-Dni: " + request.getCifDni()));

        //Actualizamos campos diferentes
        if(!request.getNombre().equals(clienteFromDb.getNombre())){
            clienteFromDb.setNombre(request.getNombre());
        }
        if (!request.getDireccion().equals(clienteFromDb.getDireccion())) {
            clienteFromDb.setDireccion(request.getDireccion());
        }
        if (!request.getCiudad().equals(clienteFromDb.getCiudad())) {
            clienteFromDb.setCiudad(request.getCiudad());
        }
        if (!request.getEmail().equals(clienteFromDb.getEmail())) {
            clienteFromDb.setEmail(request.getEmail());
        }
        if (!request.getTelefono().equals(clienteFromDb.getTelefono())) {
            clienteFromDb.setTelefono(request.getTelefono());
        }

        clienteRepository.save(clienteFromDb);

        return this.clienteToClienteRs(clienteFromDb);
    }

    @Override
    public void deleteCliente(String dniCliente, String cifDniUsuario) {
        //Recuperamos el cliente
        Cliente cliente = clienteRepository.findByCifDni(dniCliente).orElseThrow(
                () -> new ResourceNotFoundException("No hay ningun clinente con el id: " + dniCliente));

        //Comprobamos que el cliente pertenece al Usuario
        if(cliente.getUsuario().getCif().equalsIgnoreCase(cifDniUsuario)){
            System.out.println("Eliminar el cliente");
            clienteRepository.deleteById(cliente.getClienteId());
        }else{
            throw new BadRequestException("El cliente no se corresponde con ese usuario");
        }
    }

    @Override
    public ClienteRs getClienteByCifDniOEmailONombre(String keyword, Integer usuarioId) {
        Cliente clienteFromDb = clienteRepository.buscarPorNombreCifDniEmailYUsuarioIdIgnoreCase(keyword, usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Clientes with Cif-Dni: " + keyword));


        return this.clienteToClienteRs(clienteFromDb);
    }


    //====================UTIL==============

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

    //Estos metodos creo que deben ir en el serviciod e Usuario
        /*
    @Override
    public List<ClienteRs> getClientesByUsuario(String cifDni) {

        List<Cliente> clientes = clienteRepository.findAllByCifUsuarioIgnoreCase(cifDni);
        List<ClienteRs> response = new ArrayList();
        if(!clientes.isEmpty()){
            //Mapeamos a ClienteRs
            for(Cliente c : clientes){
                response.add(this.clienteToClienteRs(c));
            }
        }
        return response;
    }

    @Override
    public ClienteRs getClienteByCifDni(String cifDni) {
        return null;
    }

     */
}
