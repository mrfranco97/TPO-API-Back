package com.uade.tpo.megagame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.megagame.entity.Usuario;
import com.uade.tpo.megagame.interfaces.UsuarioInterface;

@RestController
public class UsuariosController {

    @Autowired
    private UsuarioInterface usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id).orElse(null);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {
        "nombre": "Matias Fuensalida",
        "telefono": "123456789",
        "mail": "mathias.fuensalida@hotmail.com",
        "login": "mati",
        "pass": "12345",
        "rol": {
            "id": 1
        },
        "flag_estado": true
     * }
    */
    @PostMapping("/newUsuarios")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

    /**
     *{
        "id": 1,
        "nombre": "Matias Fuensalida Actualizado",
        "telefono": "123456789",
        "mail": "mathias.fuensalida@hotmail.com",
        "login": "mati",
        "pass": "12345",
        "rol": {
            "id": 1
        },
        "flag_estado": true
     *}
     */
    @PutMapping("/usuarios")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        Usuario updatedUsuario = usuarioService.save(usuario);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
