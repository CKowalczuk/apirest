package com.complementario.apirest.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalleEvento; // (Descripción, info de auspiciantes, premio)
    private Date fechaDeCreacionEvento; //(o alta)
    private Date fechaDeCierreEvento; //(o alta)
    
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private EventoEnum estadoEvento; //: ABIERTO | EN CURSO | FINALIZADO
    
    private String suscriptorEvento; // (Emprendimientos que se registraron)
    private Long premioEvento; //: $

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="usuarioId")

    private Usuario usuario;
    
    public Evento() {
    }
    
    public Evento(Long id, String detalleEvento, Date fechaDeCreacionEvento, Date fechaDeCierreEvento,
            @NotNull EventoEnum estadoEvento, String suscriptorEvento, Long premioEvento) {
        this.id = id;
        this.detalleEvento = detalleEvento;
        this.fechaDeCreacionEvento = fechaDeCreacionEvento;
        this.fechaDeCierreEvento = fechaDeCierreEvento;
        this.estadoEvento = estadoEvento;
        this.suscriptorEvento = suscriptorEvento;
        this.premioEvento = premioEvento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalleEvento() {
        return detalleEvento;
    }

    public void setDetalleEvento(String detalleEvento) {
        this.detalleEvento = detalleEvento;
    }

    public Date getFechaDeCreacionEvento() {
        return fechaDeCreacionEvento;
    }

    public void setFechaDeCreacionEvento(Date fechaDeCreacionEvento) {
        this.fechaDeCreacionEvento = fechaDeCreacionEvento;
    }

    public Date getFechaDeCierreEvento() {
        return fechaDeCierreEvento;
    }

    public void setFechaDeCierreEvento(Date fechaDeCierreEvento) {
        this.fechaDeCierreEvento = fechaDeCierreEvento;
    }

    public EventoEnum getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EventoEnum estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public String getSuscriptorEvento() {
        return suscriptorEvento;
    }

    public void setSuscriptorEvento(String suscriptorEvento) {
        this.suscriptorEvento = suscriptorEvento;
    }

    public Long getPremioEvento() {
        return premioEvento;
    }

    public void setPremioEvento(Long premioEvento) {
        this.premioEvento = premioEvento;
    }

    @Override
    public String toString() {
        return "Evento [detalleEvento=" + detalleEvento + ", estadoEvento=" + estadoEvento + ", fechaDeCierreEvento="
                + fechaDeCierreEvento + ", fechaDeCreacionEvento=" + fechaDeCreacionEvento + ", id=" + id
                + ", premioEvento=" + premioEvento + ", suscriptorEvento=" + suscriptorEvento + "]";
    }



    
    
}
