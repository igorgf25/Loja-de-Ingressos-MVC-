package com.desafio.gft.services;

import com.desafio.gft.entities.Evento;
import com.desafio.gft.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    public Evento buscaEvento(Long id) throws Exception {
        Optional<Evento> evento = eventoRepository.findById(id);

        if (evento.isEmpty()) {
            throw new Exception("Evento não encontrado");
        }

        return evento.get();
    }

    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listaEventos() {
        return eventoRepository.findAll();
    }
}
