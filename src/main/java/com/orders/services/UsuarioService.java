package com.orders.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orders.entities.Usuario;
import com.orders.errors.ErrorService;
import com.orders.repos.UsuarioRepo;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Transactional
    public void registrar(String name, String lastname, String mail, String area, String password) throws ErrorService {

        validar(name, lastname, mail, area, password);

        Usuario usuario = new Usuario();
        usuario.setName(name);
        usuario.setLastname(lastname);
        usuario.setMail(mail);
        usuario.setArea(area);

        String encriptada = new BCryptPasswordEncoder().encode(password);
        usuario.setPassword(encriptada);

        usuarioRepo.save(usuario);

    }

    @Transactional
    public void modificar(String id, String name, String lastname, String mail, String area, String password) throws ErrorService {

        Optional<Usuario> respuesta = usuarioRepo.findById(id);

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            if(lastname != null && !lastname.equals("")) {
            	usuario.setLastname(lastname);
            }
            if(name != null && !name.equals("")) {
            	usuario.setName(name);
            }
            if(mail != null && !mail.equals("")) {
                usuario.setMail(mail);
            }
            if(area != null && !area.equals("")) {
            	usuario.setArea(area);
            }
            if(password != null && !password.equals("")) {
                String encriptada = new BCryptPasswordEncoder().encode(password);
                usuario.setPassword(encriptada);
            }

            usuarioRepo.save(usuario);

        } else {
            throw new ErrorService("No se encontró el usuario solicitado.");
        }

    }

    @Transactional
    public void delete(String id) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            usuarioRepo.delete(respuesta.get());
        }
    }

    private void validar(String name, String lastname, String mail, String area, String password) throws ErrorService {

        System.out.println("Entró a validarse.");

        if (name == null || name.isEmpty()) {
            throw new ErrorService("El nombre del usuario no puede ser nulo.");
        }
        if (lastname == null || lastname.isEmpty()) {
            throw new ErrorService("El apellido del usuario no puede ser nulo.");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorService("El mail del usuario no puede ser nulo.");
        }
        if (area == null || area.isEmpty()) {
            throw new ErrorService("El área del usuario no puede ser nula.");
        }
        if (password == null || password.isEmpty() || password.length() < 6) {
            throw new ErrorService("La clave del usuario no puede ser nulo y tiene que tener más de 6 dígitos.");
        }

        System.out.println("Terminó de validarse.");
    }

    public List<Usuario> buscarPorApellido(String lastname) {
        return usuarioRepo.listarPorApellido(lastname + "%");
    }

    public Optional<Usuario> getById(String id) {
        return usuarioRepo.findById(id);
    }

    public Optional<Usuario> getByMail(String mail) {
        return usuarioRepo.findByMail(mail);
    }

    public boolean existById(String id) {
        return usuarioRepo.existsById(id);
    }

    public boolean existByMail(String mail) {
        return usuarioRepo.existsByMail(mail);
    }
    
    @Transactional
    public Usuario buscarPorId(String id) {
        Optional<Usuario> respuesta = usuarioRepo.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        }
		return null;
    }

}
