package com.complementario.apirest.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "votos")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private VotoEnum origen;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaAlta;

    private String observacion;
    private Long emprendimientoId;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    public Voto() {
    }

    public Voto(Long id, VotoEnum origen, Usuario usuario, LocalDate fechaAlta, String observacion,
            Long emprendimientoId) {
        this.id = id;
        this.origen = origen;
        this.usuario = usuario;
        this.fechaAlta = fechaAlta;
        this.observacion = observacion;
        this.emprendimientoId = emprendimientoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VotoEnum getOrigen() {
        return origen;
    }

    public void setOrigen(VotoEnum origen) {
        this.origen = origen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getEmprendimientoId() {
        return emprendimientoId;
    }

    public void setEmprendimientoId(Long emprendimientoId) {
        this.emprendimientoId = emprendimientoId;
    }

}
