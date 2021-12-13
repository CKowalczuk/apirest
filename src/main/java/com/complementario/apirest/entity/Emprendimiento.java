package com.complementario.apirest.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
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
@Table(name = "emprendimientos")
public class Emprendimiento {

    // id (autogenerado)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nombreEmprendimiento;
    
    private String descripcionEmprendimiento;
    private String contenidoEmprendimiento; // (cuerpo de la publicación)
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaDeCreacionEmprendimiento; // (o alta)
    
    private Long objetivoEmprendimiento; // $ (recaudacion)
    private Boolean publicadoEmprendimiento; // (true o false)
    private String urlEmprendimiento; // (capturas) - puede tener 0 o varias
    private String tagsEmprendimiento; // (ejemplo: #salud, #diversion, etc. Obs: el “#” es decorado)
    private Boolean activoEmprendimiento;
   
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;
    public Emprendimiento() {
    }

    public Emprendimiento(Long id, String nombreEmprendimiento, String descripcionEmprendimiento,
            String contenidoEmprendimiento, LocalDate fechaDeCreacionEmprendimiento, Long objetivoEmprendimiento,
            Boolean publicadoEmprendimiento, String urlEmprendimiento, String tagsEmprendimiento,
            Boolean activoEmprendimiento, Long usuarioId) {
        Id = id;
        this.nombreEmprendimiento = nombreEmprendimiento;
        this.descripcionEmprendimiento = descripcionEmprendimiento;
        this.contenidoEmprendimiento = contenidoEmprendimiento;
        this.fechaDeCreacionEmprendimiento = fechaDeCreacionEmprendimiento;
        this.objetivoEmprendimiento = objetivoEmprendimiento;
        this.publicadoEmprendimiento = publicadoEmprendimiento;
        this.urlEmprendimiento = urlEmprendimiento;
        this.tagsEmprendimiento = tagsEmprendimiento;
        this.activoEmprendimiento = activoEmprendimiento;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombreEmprendimiento() {
        return nombreEmprendimiento;
    }

    public void setNombreEmprendimiento(String nombreEmprendimiento) {
        this.nombreEmprendimiento = nombreEmprendimiento;
    }

    public String getDescripcionEmprendimiento() {
        return descripcionEmprendimiento;
    }

    public void setDescripcionEmprendimiento(String descripcionEmprendimiento) {
        this.descripcionEmprendimiento = descripcionEmprendimiento;
    }

    public String getContenidoEmprendimiento() {
        return contenidoEmprendimiento;
    }

    public void setContenidoEmprendimiento(String contenidoEmprendimiento) {
        this.contenidoEmprendimiento = contenidoEmprendimiento;
    }

    public LocalDate getFechaDeCreacionEmprendimiento() {
        return fechaDeCreacionEmprendimiento;
    }

    public void setFechaDeCreacionEmprendimiento(LocalDate fechaDeCreacionEmprendimiento) {
        this.fechaDeCreacionEmprendimiento = fechaDeCreacionEmprendimiento;
    }

    public Long getObjetivoEmprendimiento() {
        return objetivoEmprendimiento;
    }

    public void setObjetivoEmprendimiento(Long objetivoEmprendimiento) {
        this.objetivoEmprendimiento = objetivoEmprendimiento;
    }

    public Boolean getPublicadoEmprendimiento() {
        return publicadoEmprendimiento;
    }

    public void setPublicadoEmprendimiento(Boolean publicadoEmprendimiento) {
        this.publicadoEmprendimiento = publicadoEmprendimiento;
    }

    public String getUrlEmprendimiento() {
        return urlEmprendimiento;
    }

    public void setUrlEmprendimiento(String urlEmprendimiento) {
        this.urlEmprendimiento = urlEmprendimiento;
    }

    public String getTagsEmprendimiento() {
        return tagsEmprendimiento;
    }

    public void setTagsEmprendimiento(String tagsEmprendimiento) {
        this.tagsEmprendimiento = tagsEmprendimiento;
    }

    public Boolean getActivoEmprendimiento() {
        return activoEmprendimiento;
    }

    public void setActivoEmprendimiento(Boolean activoEmprendimiento) {
        this.activoEmprendimiento = activoEmprendimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Emprendimiento [Id=" + Id + ", activoEmprendimiento=" + activoEmprendimiento
                + ", contenidoEmprendimiento=" + contenidoEmprendimiento + ", descripcionEmprendimiento="
                + descripcionEmprendimiento + ", fechaDeCreacionEmprendimiento=" + fechaDeCreacionEmprendimiento
                + ", nombreEmprendimiento=" + nombreEmprendimiento + ", objetivoEmprendimiento="
                + objetivoEmprendimiento + ", publicadoEmprendimiento=" + publicadoEmprendimiento
                + ", tagsEmprendimiento=" + tagsEmprendimiento + ", urlEmprendimiento=" + urlEmprendimiento + "]";
    }

}
