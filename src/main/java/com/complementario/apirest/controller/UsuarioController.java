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
// @RequestMapping("/usuarios")
public class UsuarioController implements Serializable {
    @JsonIgnore
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Alta de usuarios

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //Modificar Usuarios (Apellido y Nombre)

    @PutMapping("usuarios/{id}")
    public Usuario modificarUsuario(@PathVariable("id") Long id, @RequestBody @Valid Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).get();
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setNombre(usuario.getNombre());
        return usuarioRepository.save(usuarioExistente);
    }

    //Borrar Usuarios (BAJA FISICA)

    @DeleteMapping(value = "usuarios/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentra el Usuario a Borrar");
        }
    }

    @GetMapping("/usuarios")
    public @ResponseBody Iterable<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Listar Emprendimientos por Tags

    @GetMapping(value = "/usuarios", params = "apellido")
    public List<Usuario> usuarioBuscarApellido(@RequestParam String apellido) {
        return usuarioRepository.getByApellido(apellido);
    }
    
    @GetMapping(value = "/usuarios", params = "ciudad")
    public List<Usuario> usuarioBuscarCiudad(@RequestParam String ciudad) {
        return usuarioRepository.getByCiudad(ciudad);
    }

    // Listar Emprendimientos Inactivos
    
    @GetMapping(value = "/usuarios", params = "id")
    public List<Usuario> usuarioBuscarId(@RequestParam Long id) {
        return usuarioRepository.getById(id);
    }
    //########################## RESOLVER AMBIGUEDADES ##########################

     //Listar Usuarios por ciudad

    // @GetMapping(value = "usuarios/", params = "ciudad")
    // public List<Usuario> getUsuariosPorCiudad(@RequestParam String ciudad) {
    //     return usuarioRepository.getByCiudad(ciudad);
    // }
 
    // //Listar Usuarios por Apellido

    // @GetMapping(value = "usuarios/", params = "apellido")
    // public List<Usuario> getUsuariosPorApellido(@RequestParam String apellido) {
    //     return usuarioRepository.getByApellido(apellido);
    // }

    // //Listar usuarios por Id

    // @GetMapping(value = "usuarios/", params = "id")
    // public Usuario getUsuariosPorId(@RequestParam Long id) {
    //     return usuarioRepository.getById(id);
    //     // return new ResponseEntity<>(usuarioRepository.findById(id),HttpStatus.OK);
    // }

    // @GetMapping("usuarios/")
    // public @ResponseBody Iterable<Usuario> buscarUsuarios() {
    //     return usuarioRepository.findAll();
    // }

    
//########################## RESOLVER AMBIGUEDADES ##########################

}