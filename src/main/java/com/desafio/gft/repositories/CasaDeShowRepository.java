package com.desafio.gft.repositories;

import com.desafio.gft.entities.CasaDeShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaDeShowRepository extends JpaRepository<CasaDeShow, Long> {
}
