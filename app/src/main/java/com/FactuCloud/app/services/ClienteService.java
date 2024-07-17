package com.FactuCloud.app.services;

import com.FactuCloud.app.dto.request.ClienteRq;
import com.FactuCloud.app.dto.response.ClienteRs;

public interface ClienteService {

    ClienteRs crearCliente(ClienteRq request);
    ClienteRs updateCliente(ClienteRq request);
    void deleteCliente(String dniCliente, String cifDniUsuario);
    ClienteRs getClienteByCifDniOEmailONombre(String keyword, Integer usuarioId);
    //Creo que deben ir en el servicio de Usuario
    /*
    List<ClienteRs> getClientesByUsuario(String cifDni);
    ClienteRs getClienteByCifDni(String cifDni);

     */
}
