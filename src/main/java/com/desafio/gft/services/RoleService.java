package com.desafio.gft.services;

import com.desafio.gft.entities.Role;
import com.desafio.gft.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role buscaRole(String role) throws Exception {
        Optional<Role> r = roleRepository.findById(role);

        if (r.isEmpty()) {
            throw new Exception("Role não encontrado");
        }

        return r.get();
    }
}
