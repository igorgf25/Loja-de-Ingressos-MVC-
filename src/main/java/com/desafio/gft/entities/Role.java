package com.desafio.gft.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    private String nomeRole;

    @OneToMany()
    private List<Usuario> usuarios;

    public Role(String role) {
        this.nomeRole = role;
        usuarios = new ArrayList<Usuario>();
    }

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}
