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

    @Autowired
    EventoService eventoService;

    public Ingresso salvarIngresso(Evento evento, Long quantidade) throws Exception {
        Ingresso ingresso = new Ingresso();

        ingresso.setData(evento.getData());
        ingresso.setNome(evento.getNome());
        ingresso.setHoras(evento.getHoras());
        ingresso.setQuantidade(quantidade);

        Usuario usuario;

        try {
            usuario = usuarioService.buscarUsuarioLogado();
            ingresso.setUsuario(usuario);
        } catch (Exception e) {
            throw new Exception("Erro ao buscar usuario: " + e.getMessage());
        }

        BigDecimal valorTotal = evento.getValorIngresso().multiply(new BigDecimal(quantidade));

        ingresso.setValor(valorTotal);

        eventoService.diminuirIngressos(evento.getId(), quantidade);

        return ingressoRepository.save(ingresso);
    }

    public void excuirIngresso(Long id) {
        ingressoRepository.deleteById(id);
    }
}
