package com.desafio.gft.services;

import com.desafio.gft.entities.Evento;
import com.desafio.gft.entities.Ingresso;
import com.desafio.gft.entities.Usuario;
import com.desafio.gft.repositories.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Service
public class IngressoService {

    @Autowired
    IngressoRepository ingressoRepository;

    @Autowired
    UsuarioService usuarioService;

    public Ingresso salvarIngresso(Evento evento, Integer quantidade) throws Exception {
        Ingresso ingresso = new Ingresso();

        ingresso.setEvento(evento);
        ingresso.setData(evento.getData());
        ingresso.setNome(evento.getNome());
        ingresso.setQuantidade(quantidade);

        Usuario usuario;

        try {
            usuario = usuarioService.buscarUsuarioLogado();
            ingresso.setUsuario(usuario);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar usuario: " + e.getMessage());
        }

        System.out.println(evento.getNome());

        BigDecimal valorTotal = evento.getValorIngresso().multiply(new BigDecimal(quantidade));

        ingresso.setValor(valorTotal);

        return ingressoRepository.save(ingresso);
    }

    public void excuirIngresso(Long id) {
        ingressoRepository.deleteById(id);
    }
}