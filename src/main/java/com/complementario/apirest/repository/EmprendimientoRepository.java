package com.complementario.apirest.repository;

import java.util.List;

import com.complementario.apirest.entity.Emprendimiento;

import org.springframework.data.repository.CrudRepository;

public interface EmprendimientoRepository extends CrudRepository<Emprendimiento, Long> {
    List<Emprendimiento> findByUsuario(Long Id);
    List<Emprendimiento> getByTagsEmprendimiento(String emprendimientosAFiltrar);
    List<Emprendimiento> getByPublicadoEmprendimiento(Boolean emprendimientosAFiltrar );
    
}
