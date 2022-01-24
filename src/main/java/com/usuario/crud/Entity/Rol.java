package com.usuario.crud.Entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="rol")
public class Rol {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID_ROL")
    private int id;

    @Column(name="NOMBRE")
    @NotNull
    private String nombre;

    public Rol() {
    }

    public Rol(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
