package com.desafio.gft.repositories;

import com.desafio.gft.entities.Evento;
import com.desafio.gft.entities.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
