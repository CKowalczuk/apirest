package com.complementario.apirest.controller;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.complementario.apirest.entity.Usuario;
import com.complementario.apirest.entity.Voto;
import com.complementario.apirest.repository.UsuarioRepository;
import com.complementario.apirest.repository.VotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotoController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private VotoRepository votoRepository;

    // // Alta de Voto

    public VotoController(UsuarioRepository usuarioRepository, VotoRepository votoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.votoRepository = votoRepository;
    }

    @Transactional
    @PostMapping("/usuarios/{id}/votos")
    public ResponseEntity<?> crearVoto(@PathVariable("id") Long id,
            @RequestBody @Valid Voto voto) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.getVotos().add(voto);
        voto.setUsuario(usuario);

        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    // Listar Todos los Votos
    @GetMapping("/votos")
    public ResponseEntity<?> buscarVotos() {
        return new ResponseEntity<>(votoRepository.findAll(), HttpStatus.OK);

    }

    // Listar los Votos de Un usuario
    @GetMapping(value = "/votos", params = "usuario")
    public ResponseEntity<?> votosAFiltrar(@RequestParam Usuario usuario) {
        return new ResponseEntity<>(votoRepository.findByUsuario(usuario), HttpStatus.OK);
    }
}
