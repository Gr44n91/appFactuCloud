package com.FactuCloud.app.controllers;

import com.FactuCloud.app.dto.response.ClienteRs;
import com.FactuCloud.app.dto.request.usuario.UsuarioRq;
import com.FactuCloud.app.dto.response.UsuarioRs;
import com.FactuCloud.app.exceptions.BadRequestException;
import com.FactuCloud.app.services.UsuarioService;
import com.FactuCloud.app.util.ValidarDniCif;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ValidarDniCif validarDniCif = new ValidarDniCif();

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioRs> crearUsuario(@Valid @RequestBody UsuarioRq request){

        if(validarDniCif.validarLetra(request.getCifDni())){
            return ResponseEntity.ok(usuarioService.crearUsuario(request));
        }
        throw new BadRequestException("El CIF o DNI es incorrecto");
    }

    @PutMapping
    public ResponseEntity<UsuarioRs> updateUsuario(@Valid @RequestBody UsuarioRq request){

        if(validarDniCif.validarLetra(request.getCifDni())) {
            return ResponseEntity.ok(usuarioService.updateUsuario(request));
        }
        throw new BadRequestException("El Cif o Dni es incorrecto");
    }

    @DeleteMapping
    public ResponseEntity<UsuarioRs> deleteUsuario(@RequestParam Integer id) {

        if (id <= 0) {
            throw new BadRequestException("El Id debe ser mayor que 0");
        } else {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        }
    }

    //mis-clientes de usuarios

    @GetMapping("mis-clientes")
    public ResponseEntity<List<ClienteRs>> getMisClientes(@RequestParam String cifDni) {

        if(!validarDniCif.validarCifDni(cifDni)){
            throw new BadRequestException("El Dni o Cif no tiene el formato correcto");
        }else if(!validarDniCif.validarLetra(cifDni)){
            throw new BadRequestException("El Dni o Cif no es correcto");
        }else{
            return ResponseEntity.ok(usuarioService.getClientesByUsuario(cifDni));
        }
    }
}
