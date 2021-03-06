package com.complementario.apirest.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String apellido;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaAlta;

    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    private String password;
    private String ciudad;
    private String provincia;
    private String país;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.ALL)
    public List<Emprendimiento> emprendimientos = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    public List<Voto> votos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    public List<Evento> eventos = new ArrayList<>();

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private UsuarioEnum tipo;

    public Usuario(Long id, String nombre, String apellido, LocalDate fechaAlta,
            @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") String email,
            String password, String ciudad, String provincia, String país, List<Emprendimiento> emprendimientos,
            List<Voto> votos, List<Evento> eventos, @NotNull UsuarioEnum tipo) {
        Id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaAlta = fechaAlta;
        this.email = email;
        this.password = password;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.país = país;
        this.emprendimientos = emprendimientos;
        this.votos = votos;
        this.eventos = eventos;
        this.tipo = tipo;
    }

    public Usuario() {
    }

    public List<Emprendimiento> getEmprendimiento() {
        return emprendimientos;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setEmprendimiento(List<Emprendimiento> emprendimientos) {
        this.emprendimientos = emprendimientos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPaís() {
        return país;
    }

    public void setPaís(String país) {
        this.país = país;
    }

    public UsuarioEnum getTipo() {
        return tipo;
    }

    public void setTipo(UsuarioEnum tipo) {
        this.tipo = tipo;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void agregarEmprendimiento(Emprendimiento emprendimiento) {
        this.emprendimientos.add(emprendimiento);
        emprendimiento.setUsuario(this);
    }

    public void agregarVoto(Voto voto) {
        this.votos.add(voto);
        voto.setUsuario(this);
    }

    public void agregarEvento(Evento evento) {
        this.eventos.add(evento);
        evento.setUsuario(this);
    }

    @Override
    public String toString() {
        return "Usuario [Id=" + Id + ", apellido=" + apellido + ", ciudad=" + ciudad + ", email=" + email
                + ", emprendimientos=" + emprendimientos + ", eventos=" + eventos + ", fechaAlta=" + fechaAlta
                + ", nombre=" + nombre + ", password=" + password + ", país=" + país + ", provincia=" + provincia
                + ", tipo=" + tipo + ", votos=" + votos + "]";
    }

   

}
