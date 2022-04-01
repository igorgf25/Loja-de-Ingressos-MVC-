package com.desafio.gft.controllers;

import com.desafio.gft.entities.Role;
import com.desafio.gft.entities.Usuario;
import com.desafio.gft.services.RoleService;
import com.desafio.gft.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    RoleService roleService;

    @Autowired
    UsuarioService usuarioService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @RequestMapping("form")
    public ModelAndView formUsuario() {
        ModelAndView mv = new ModelAndView("usuario/form.html");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @RequestMapping(path = "form", method = RequestMethod.POST)
    public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("usuario/form.html");

        if (bindingResult.hasErrors()) {
            mv.addObject("usuario", usuario);
            return mv;
        }

        Role role;

        try {
            role = roleService.buscaRole("ROLE_USER");
        } catch (Exception e) {
            mv.addObject("message", "Erro ao cadastrar" + e.getMessage());
            return mv;
        }

        List<Role> roles = new ArrayList<Role>();
        roles.add(role);

        usuario.setRoles(roles);
        usuario.setSenha(encoder.encode(usuario.getPassword()));

        usuarioService.salvarUsuario(usuario);

        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @RequestMapping("formadmin")
    public ModelAndView formAdministrador() {
        ModelAndView mv = new ModelAndView("usuario/formAdmin.html");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @RequestMapping(path = "formadmin", method = RequestMethod.POST)
    public ModelAndView salvarAdmin(@Valid Usuario usuario, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("usuario/formAdmin.html");

        if (bindingResult.hasErrors()) {
            mv.addObject("usuario", usuario);
            return mv;
        }

        Role role;

        try {
            role = roleService.buscaRole("ROLE_ADMIN");
        } catch (Exception e) {
            mv.addObject("message", "Erro ao cadastrar" + e.getMessage());
            return mv;
        }

        List<Role> roles = new ArrayList<Role>();
        roles.add(role);

        usuario.setRoles(roles);
        usuario.setSenha(encoder.encode(usuario.getPassword()));

        usuarioService.salvarUsuario(usuario);

        mv.addObject("usuario", new Usuario());
        return mv;
    }
}
