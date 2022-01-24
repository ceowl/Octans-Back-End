package com.usuario.crud.Entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID_USUARIO")
    private int id;

    @JoinColumn(name = "ID_ROL")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Rol rol;

    @Column(name="NOMBRE")
    @NotNull
    private String nombre;

    @Column(name="ACTIVO")
    @NotNull
    private String activo;

    public Usuario() {
    }

    public Usuario(String activo, String nombre, Rol rol) {
        this.rol = rol;
        this.nombre = nombre;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
