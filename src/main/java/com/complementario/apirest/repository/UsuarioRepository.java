package com.complementario.apirest.repository;

import java.util.List;

import com.complementario.apirest.entity.Usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    Usuario  getById(Long id);
    List<Usuario> getByCiudad(String ciudad);
    List<Usuario> getByApellido(String apellido);
}

