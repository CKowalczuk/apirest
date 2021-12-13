package com.complementario.apirest.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.complementario.apirest.entity.Emprendimiento;
import com.complementario.apirest.entity.Usuario;
import com.complementario.apirest.repository.EmprendimientoRepository;
import com.complementario.apirest.repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmprendimientoController {
    private UsuarioRepository usuarioRepository;
    private EmprendimientoRepository emprendimientoRepository;

    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository,
            UsuarioRepository usuarioRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Alta de Emprendimientos

    @PostMapping("/usuarios/{id}/emprendimientos")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("id") Long id,
            @RequestBody @Valid Emprendimiento emprendimiento) {
                Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.getEmprendimiento().add(emprendimiento);
        emprendimiento.setUsuario(usuario);

        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    // Baja FISICA de Emprendimientos

    // @DeleteMapping(value = "/emprendimientos/{id}")
    // public void borrarEmprendimiento(@PathVariable Long id) {
    // try {
    // emprendimientoRepository.deleteById(id);
    // } catch (EmptyResultDataAccessException e) {
    // throw new IllegalArgumentException("No se encuentra el Emprendimiento a
    // Borrar");
    // }
    // }

    // Baja LOGICA de Emprendimientos (seteando false setActivoEmprendimiento)
    // Modificación de Emprendimientos

    @PutMapping("/emprendimientos/{id}")
    public ResponseEntity<?> modificarEmprendimiento(@PathVariable("id") Long id,
            @RequestBody @Valid Emprendimiento emprendimiento) {
        Emprendimiento emprendimientoExistente = emprendimientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
        emprendimientoExistente.setNombreEmprendimiento(emprendimiento.getNombreEmprendimiento());
        emprendimientoExistente.setDescripcionEmprendimiento(emprendimiento.getDescripcionEmprendimiento());
        emprendimientoExistente.setContenidoEmprendimiento(emprendimiento.getContenidoEmprendimiento());
        emprendimientoExistente.setObjetivoEmprendimiento(emprendimiento.getObjetivoEmprendimiento());
        emprendimientoExistente.setPublicadoEmprendimiento(emprendimiento.getPublicadoEmprendimiento());
        emprendimientoExistente.setUrlEmprendimiento(emprendimiento.getUrlEmprendimiento());
        emprendimientoExistente.setTagsEmprendimiento(emprendimiento.getTagsEmprendimiento());
        emprendimientoExistente.setActivoEmprendimiento(emprendimiento.getActivoEmprendimiento());
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimientoExistente), HttpStatus.OK);

    }

    // Consulta de Todos los Emprendimientos

    @GetMapping("/emprendimientos")
    public ResponseEntity<?> buscarEmprendimientos() {
        return new ResponseEntity<>(emprendimientoRepository.findAll(), HttpStatus.OK);

    }

    // Listar Emprendimientos por Tags

    @GetMapping(value = "/emprendimientos", params = "tagsEmprendimiento")
    public ResponseEntity<?> emprendimientosAFiltrar(@RequestParam String tagsEmprendimiento) {
        return new ResponseEntity<>(emprendimientoRepository.getByTagsEmprendimiento(tagsEmprendimiento), HttpStatus.OK);

    }

    // Listar Emprendimientos Inactivos
    
    @GetMapping(value = "/emprendimientos", params = "publicadoEmprendimiento")
    public ResponseEntity<?> emprendimientosAFiltrar(@RequestParam Boolean publicadoEmprendimiento) {
        return new ResponseEntity<>(emprendimientoRepository.getByPublicadoEmprendimiento(publicadoEmprendimiento), HttpStatus.OK);

    }
}