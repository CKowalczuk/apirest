package com.complementario.apirest.controller;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.complementario.apirest.entity.Evento;
import com.complementario.apirest.entity.Usuario;
import com.complementario.apirest.repository.EventoRepository;
import com.complementario.apirest.repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventoController {
    private UsuarioRepository usuarioRepository;
    private EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository,
            UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Alta de Eventos
    @Transactional
    @PostMapping("/usuarios/{id}/eventos")
    public ResponseEntity<?> crearEvento(@PathVariable("id") Long id,
            @RequestBody @Valid Evento evento) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.getEventos().add(evento);
        evento.setUsuario(usuario);

        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    // modificacion o baja de eventos

    @PutMapping("/eventos/{id}")
    public ResponseEntity<?> modificarEvento(@PathVariable("id") Long id,
            @RequestBody @Valid Evento evento) {
        Evento eventoExistente = eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
        eventoExistente.setDetalleEvento(evento.getDetalleEvento());
        eventoExistente.setFechaDeCierreEvento(evento.getFechaDeCierreEvento());
        eventoExistente.setEstadoEvento(evento.getEstadoEvento());
        eventoExistente.setSuscriptorEvento(evento.getSuscriptorEvento());
        eventoExistente.setPremioEvento(evento.getPremioEvento());

        return new ResponseEntity<>(eventoRepository.save(eventoExistente), HttpStatus.OK);
    }

    // Consulta de Todos los Eventos

    @GetMapping("/eventos")
    public ResponseEntity<?> buscarEeventos() {
        return new ResponseEntity<>(eventoRepository.findAll(), HttpStatus.OK);

    }

}
