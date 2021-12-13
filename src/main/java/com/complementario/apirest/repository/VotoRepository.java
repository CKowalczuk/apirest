package com.complementario.apirest.repository;

import java.util.List;

import com.complementario.apirest.entity.Voto;

import org.springframework.data.repository.CrudRepository;

public interface VotoRepository extends CrudRepository<Voto, Long>{
    List<Voto> findByUsuario(Long Id);
    List<Voto> findVotoById(Long Id);
    List<Voto> findAllById(Long Id);
}
