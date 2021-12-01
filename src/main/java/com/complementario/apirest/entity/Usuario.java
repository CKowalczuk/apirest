package com.complementario.apirest.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
    private LocalDateTime fechaAlta;

    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    private String password;
    private String ciudad;
    private String provincia;
    private String país;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprendimiento> emprendimiento = new ArrayList<>();

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private UsuarioEnum tipo;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, LocalDateTime fechaAlta,
            @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") String email,
            String password, String ciudad, String provincia, String país, List<Emprendimiento> emprendimiento,
            @NotNull UsuarioEnum tipo) {
        Id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaAlta = fechaAlta;
        this.email = email;
        this.password = password;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.país = país;
        this.emprendimiento = emprendimiento;
        this.tipo = tipo;
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
    
    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<Emprendimiento> getEmprendimiento() {
        return emprendimiento;
    }

    public void setEmprendimiento(List<Emprendimiento> emprendimiento) {
        this.emprendimiento = emprendimiento;
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

    @Override
    public String toString() {
        return "Usuario [Id=" + Id + ", apellido=" + apellido + ", ciudad=" + ciudad + ", email=" + email
                + ", fechaAlta=" + fechaAlta + ", nombre=" + nombre + ", país=" + país
                + ", provincia=" + provincia + ", tipo=" + tipo + "]";
    }

}
