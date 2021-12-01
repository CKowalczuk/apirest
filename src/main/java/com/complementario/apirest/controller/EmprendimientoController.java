package com.complementario.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import com.complementario.apirest.entity.Emprendimiento;
import com.complementario.apirest.entity.Usuario;
import com.complementario.apirest.repository.EmprendimientoRepository;
import com.complementario.apirest.repository.UsuarioRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmprendimientoController {

    EmprendimientoRepository emprendimientoRepository;
    UsuarioRepository usuarioRepository;

    // Alta de Emprendimientos

    @PostMapping("/usuarios/{id}/emprendimientos")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("id") Long id,
            @RequestBody @Valid Emprendimiento emprendimiento) {

        Usuario usuario = usuarioRepository.getById(id);
        Emprendimiento emprendimiento1 = emprendimientoRepository.findById(id).get();
        emprendimiento1.setUsuario(usuario);

        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento1), HttpStatus.CREATED);
    }

    // #1 Buscar si el emprendimiento ya no existe
    // #2 Buscar el usuario con su repository
    // #3 crear un Emprendimiento -> new Empredimiento(....)
    // #4 Agregar al usuario encontrado antes este emprendimiento a su lista

    // Baja FISICA de Emprendimientos
    @DeleteMapping(value = "/emprendimientos/{id}")
    public void borrarEmprendimiento(@PathVariable Long id) {
        try {
            emprendimientoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentra el Emprendimiento a Borrar");
        }
    }
    // //Baja LOGICA de Emprendimientos
    // @RequestMapping(value = "/emprendimientos/{id}", method = RequestMethod.PUT)
    // public Emprendimiento bajaLogicaEmprendimiento(@PathVariable("id") Long id,
    // @RequestBody @Valid Emprendimiento emprendimiento) {
    // Emprendimiento emprendimientoABajar = repository.findById(id).get();
    // emprendimientoABajar.setActivoEmprendimiento(false);
    // return repository.save(emprendimientoABajar);
    // }
    // Modificación de Emprendimientos

    @RequestMapping(value = "/emprendimientos/{id}", method = RequestMethod.PUT)
    public Emprendimiento modificarEmprendimiento(@PathVariable("id") Long id,
            @RequestBody @Valid Emprendimiento emprendimiento) {
        Emprendimiento emprendimientoExistente = emprendimientoRepository.findById(id).get();
        emprendimientoExistente.setNombreEmprendimiento(emprendimiento.getNombreEmprendimiento());
        emprendimientoExistente.setDescripcionEmprendimiento(emprendimiento.getDescripcionEmprendimiento());
        emprendimientoExistente.setContenidoEmprendimiento(emprendimiento.getContenidoEmprendimiento());
        emprendimientoExistente.setObjetivoEmprendimiento(emprendimiento.getObjetivoEmprendimiento());
        emprendimientoExistente.setPublicadoEmprendimiento(emprendimiento.getPublicadoEmprendimiento());
        emprendimientoExistente.setUrlEmprendimiento(emprendimiento.getUrlEmprendimiento());
        emprendimientoExistente.setTagsEmprendimiento(emprendimiento.getTagsEmprendimiento());
        emprendimientoExistente.setActivoEmprendimiento(emprendimiento.getActivoEmprendimiento());
        return emprendimientoRepository.save(emprendimientoExistente);
    }

    // Consulta de Todos los Emprendimientos
    @RequestMapping(value = "/emprendimientos", method = RequestMethod.GET)
    public @ResponseBody Iterable<Emprendimiento> buscarEmprendimientos() {
        return emprendimientoRepository.findAll();
    }

    @RequestMapping(value = "/emprendimientos", params = "tagsEmprendimiento")
    public List<Emprendimiento> emprendimientosAFiltrar(@RequestParam String tagsEmprendimiento) {

        return emprendimientoRepository.getByTagsEmprendimiento(tagsEmprendimiento);
    }

    @RequestMapping(value = "/emprendimientos", params = "publicadoEmprendimiento")
    public List<Emprendimiento> emprendimientosAFiltrar(@RequestParam Boolean publicadoEmprendimiento) {

        return emprendimientoRepository.getByPublicadoEmprendimiento(publicadoEmprendimiento);
    }
}