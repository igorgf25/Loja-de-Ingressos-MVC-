package com.desafio.gft.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
public class CasaDeShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome da casa de show não pode estar vazio")
    private String nome;

    @NotEmpty(message = "Endereço da casa de show não pode estar vazio")
    private String endereco;

    @OneToMany(mappedBy = "casaDeShow")
    private List<Evento> eventos;
}
