package com.desafio.gft.services;

import com.desafio.gft.entities.Ingresso;
import com.desafio.gft.repositories.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngressoService {

    @Autowired
    IngressoRepository ingressoRepository;

    public Ingresso salvarIngresso(Ingresso ingresso) {
        return ingressoRepository.save(ingresso);
    }

    public void excuirIngresso(Long id) {
        ingressoRepository.deleteById(id);
    }
}
