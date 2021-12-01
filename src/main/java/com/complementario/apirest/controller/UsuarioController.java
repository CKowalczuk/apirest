package com.complementario.apirest.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.complementario.apirest.entity.Usuario;
import com.complementario.apirest.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController implements Serializable {
    @Autowired
    private UsuarioRepository repository;

    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public @ResponseBody Iterable<Usuario> findEmpleados() {
        return repository.findAll();
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuarioPorId(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.PUT)
    public Usuario modificarUsuario(@PathVariable("id") Long id, @RequestBody @Valid Usuario usuario) {
        Usuario usuarioExistente = repository.findById(id).get();
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setNombre(usuario.getNombre());
        return repository.save(usuarioExistente);
    }

    @DeleteMapping(value = "/usuarios/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentra el Usuario a Borrar");
        }
    }

    @RequestMapping(value = "/usuarios", params = "ciudad")
    public List<Usuario> Ciudades(@RequestParam String ciudad) {

        return repository.getByCiudad(ciudad);
    }

    @RequestMapping(value = "/usuarios", params = "apellido")
    public List<Usuario> Apellidos(@RequestParam String apellido) {

        return repository.getByApellido(apellido);
    }
}