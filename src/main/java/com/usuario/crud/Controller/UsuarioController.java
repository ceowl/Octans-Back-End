package com.usuario.crud.Controller;

import com.usuario.crud.Dto.Mensaje;
import com.usuario.crud.Dto.UsuarioDto;
import com.usuario.crud.Entity.Usuario;
import com.usuario.crud.Service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins="http://localhost:4200")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> list = usuarioService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id) {
        if (!usuarioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        } else {
            Usuario usuario = usuarioService.getOne(id).get();
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Usuario> getByNombre(@PathVariable("nombre") String nombre){
            if (!usuarioService.existsByNombre(nombre)) {
                return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
            } else {
                Usuario usuario = usuarioService.getByNombre(nombre).get();
                return new ResponseEntity(usuario, HttpStatus.OK);
            }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto){
        if(StringUtils.isBlank(usuarioDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(usuarioDto.getRol() == null || usuarioDto.getRol().getId()>3)
            return new ResponseEntity(new Mensaje("El rol es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuarioDto.getActivo()))
            return new ResponseEntity(new Mensaje("El estado activo es obligatorio"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombre(usuarioDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario = new Usuario(usuarioDto.getActivo(), usuarioDto.getNombre(), usuarioDto.getRol());
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario creado satisfactoriamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody UsuarioDto usuarioDto){
        if(!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombre(usuarioDto.getNombre()) && usuarioService.getByNombre(usuarioDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuarioDto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(usuarioDto.getRol().getId()>3)
            return new ResponseEntity(new Mensaje("El rol es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuarioDto.getActivo()))
            return new ResponseEntity(new Mensaje("El estado activo es obligatorio"), HttpStatus.BAD_REQUEST);


        Usuario usuario = usuarioService.getOne(id).get();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setRol(usuarioDto.getRol());
        usuario.setActivo(usuarioDto.getActivo());
        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario actualizado satisfactoriamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("El usuario no existe"), HttpStatus.BAD_REQUEST);
        usuarioService.delete(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado satisfactoriamente"), HttpStatus.OK);
    }
}


