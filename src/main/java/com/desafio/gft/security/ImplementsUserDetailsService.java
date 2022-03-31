package com.desafio.gft.security;

import com.desafio.gft.entities.Usuario;
import com.desafio.gft.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(username);

        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }
        return new User(usuario.get().getUsername(), usuario.get().getPassword(), true, true, true, true, usuario.get().getAuthorities());
    }
}
