package com.desafio.gft.services;

import com.desafio.gft.entities.CasaDeShow;
import com.desafio.gft.repositories.CasaDeShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CasaDeShowService {
    @Autowired
    CasaDeShowRepository casaDeShowRepository;

    public CasaDeShow salvarCasaDeShow(CasaDeShow casaDeShow) {
        return casaDeShowRepository.save(casaDeShow);
    }

    public CasaDeShow buscarCasaDeShow(Long id) throws Exception {
        Optional<CasaDeShow> casaDeShow = casaDeShowRepository.findById(id);

        if (casaDeShow.isEmpty()) {
            throw new Exception("Casa de show não encontrada");
        }

        return casaDeShow.get();
    }

    public List<CasaDeShow> listaCasaDeShow() {
        return casaDeShowRepository.findAll();
    }

    public void excluirCasaDeShow(Long id) {
        casaDeShowRepository.deleteById(id);
    }
}
