package com.complementario.apirest.controller;

import java.io.Serializable;

import javax.validation.Valid;

import com.complementario.apirest.entity.Usuario;
import com.complementario.apirest.repository.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/usuarios")
public class UsuarioController implements Serializable {
    @JsonIgnore
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Alta de usuarios

    @PostMapping("/usuarios")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    // Modificar Usuarios (Apellido y Nombre)

    @PutMapping("usuarios/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable("id") Long id, @RequestBody @Valid Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).get();
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setNombre(usuario.getNombre());
        return new ResponseEntity<>(usuarioRepository.save(usuarioExistente), HttpStatus.OK);
    }

    // Borrar Usuarios (BAJA FISICA)

    @DeleteMapping(value = "/usuarios/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentra el Usuario a Borrar");
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> buscarUsuarios() {
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    // Listar Emprendimientos por Tags

    @GetMapping(value = "/usuarios", params = "apellido")
    public ResponseEntity<?> usuarioBuscarApellido(@RequestParam String apellido) {
        return new ResponseEntity<>(usuarioRepository.getByApellido(apellido), HttpStatus.OK);
    }

    @GetMapping(value = "/usuarios", params = "ciudad")
    public ResponseEntity<?> usuarioBuscarCiudad(@RequestParam String ciudad) {
        return new ResponseEntity<>(usuarioRepository.getByCiudad(ciudad), HttpStatus.OK);
    }

    // Listar Emprendimientos Inactivos

    @GetMapping(value = "/usuarios", params = "id")
    public ResponseEntity<?> usuarioBuscarId(@RequestParam Long id) {
        return new ResponseEntity<>(usuarioRepository.getById(id), HttpStatus.OK);
    }
}