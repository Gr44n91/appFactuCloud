package com.FactuCloud.app.controllers;

import com.FactuCloud.app.dto.request.ClienteRq;
import com.FactuCloud.app.dto.response.ClienteRs;
import com.FactuCloud.app.exceptions.BadRequestException;
import com.FactuCloud.app.services.ClienteService;
import com.FactuCloud.app.util.ValidarDniCif;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ValidarDniCif validarDniCif = new ValidarDniCif();

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //El controlador de clientes lo vamos a usar para CRUD clientes pero en el controlador de
    //usuario solo podremos obtener una lista de mis-clientes.
    @PostMapping
    public ResponseEntity<ClienteRs> crearCliente(@Valid @RequestBody ClienteRq request){

        if(request.getCifDni() == null){
            throw new BadRequestException("El cif del cliente es obligatorio");
        }
        if(validarDniCif.validarLetra(request.getCifDni()) && validarDniCif.validarLetra(request.getCifUsuario())){
            return ResponseEntity.ok(clienteService.crearCliente(request));
        }

        throw new BadRequestException("El DNI o CIF no es correcto");
    }

    @PutMapping
    public ResponseEntity<ClienteRs> updateCliente(@Valid @RequestBody ClienteRq request) {

        return ResponseEntity.ok(clienteService.updateCliente(request));
    }

    @DeleteMapping
    public ResponseEntity<ClienteRs> deleteCliente(@RequestParam String dniCliente, @RequestParam String cifDniUsuario) {

        if(!validarDniCif.validarCifDni(dniCliente) && validarDniCif.validarCifDni(cifDniUsuario)){
            throw new BadRequestException("El Dni o Cif no tiene el formato correcto");
        }else if(!validarDniCif.validarLetra(dniCliente) && !validarDniCif.validarLetra(cifDniUsuario)){
            throw new BadRequestException("El Dni o Cif no es correcto");
        }else{
            clienteService.deleteCliente(dniCliente, cifDniUsuario);
            return ResponseEntity.noContent().build();
        }

    }
    @GetMapping("/cliente")
    public ResponseEntity<ClienteRs> getClienteByCifDniEmailOrNombre(@RequestParam String keyword,
                                                                     @RequestParam Integer usuarioId){
        //Parametros para busqueda: cifDni, email, nombre
        return ResponseEntity.ok(clienteService.getClienteByCifDniOEmailONombre(keyword, usuarioId));
    }


    //Estos metodos van en el controller de usuario


    /*
    @GetMapping("/mis-clientes")
    public ResponseEntity<List<ClienteRs>> getClientes(@RequestParam(name = "cifDni") String cifDni) {

        if(!validarDniCif.validarCifDni(cifDni)){
            throw new BadRequestException("El Dni o Cif no tiene el formato correcto");
        }else if(!validarDniCif.validarLetra(cifDni)){
            throw new BadRequestException("El Dni o Cif no es correcto");
        }
        else {
            return ResponseEntity.ok(clienteService.getClientesByUsuario(cifDni));
        }
    }

     */

}
