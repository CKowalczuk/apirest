package com.complementario.apirest.repository;

import java.util.List;

import com.complementario.apirest.entity.Evento;

import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, Long> {
        List<Evento> findByUsuario(Long Id);
        List<Evento> findAllById(Long Id);
}

