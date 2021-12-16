package com.complementario.apirest.repository;

import java.util.List;

import com.complementario.apirest.entity.Usuario;
import com.complementario.apirest.entity.Voto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long>{
    List<Voto> findByUsuario(Usuario usuario);
    List<Voto> findVotoById(Long id);
    List<Voto> findAllById(Long id);
}
