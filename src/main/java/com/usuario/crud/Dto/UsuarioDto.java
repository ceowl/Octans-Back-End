package com.usuario.crud.Dto;

import com.sun.istack.NotNull;
import com.usuario.crud.Entity.Rol;

public class UsuarioDto {

    @NotNull
    private Rol rol;

    @NotNull
    private String nombre;

    @NotNull
    private String activo;

    public UsuarioDto() {
    }

    public UsuarioDto(Rol rol, String nombre, String activo) {
        this.rol = rol;
        this.nombre = nombre;
        this.activo = activo;
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
