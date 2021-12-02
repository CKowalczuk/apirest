package com.complementario.apirest.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.complementario.apirest.entity.Usuario;
import com.complementario.apirest.repository.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController implements Serializable {
    @JsonIgnore
    @Autowired
    private UsuarioRepository repository;

    //Alta de usuarios

    @PostMapping("/usuarios")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    //Modificar Usuarios (Apellido y Nombre)

    @PutMapping("/usuarios/{id}")
    public Usuario modificarUsuario(@PathVariable("id") Long id, @RequestBody @Valid Usuario usuario) {
        Usuario usuarioExistente = repository.findById(id).get();
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setNombre(usuario.getNombre());
        return repository.save(usuarioExistente);
    }

    //Borrar Usuarios (BAJA FISICA)

    @DeleteMapping(value = "/usuarios/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentra el Usuario a Borrar");
        }
    }

    //Listar todos los usuarios

    @GetMapping("/usuarios")
    public @ResponseBody Iterable<Usuario> findUsuarios() {
        return repository.findAll();
    }
    //########################## RESOLVER AMBIGUEDADES ##########################
    //Listar usuarios por Id

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioPorId(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }
    
    //Listar Usuarios por ciudad

    @GetMapping(value = "/usuarios/{ciudad}")
    public List<Usuario> Ciudades(@RequestParam String ciudad) {

        return repository.getByCiudad(ciudad);
    }

    //Listar Usuarios por Apellido

    @GetMapping(value = "/usuarios/{apellido}")
    public List<Usuario> Apellidos(@RequestParam String apellido) {

        return repository.getByApellido(apellido);
    }

//########################## RESOLVER AMBIGUEDADES ##########################

}